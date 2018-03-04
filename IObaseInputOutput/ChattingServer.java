package day16.IObaseInputOutput;


import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
//Application javaFX�� ����.
public class ChattingServer extends Application {
	//Threadpool�� ����
	//socket�� accept�� connect Ȥ�� inputstream�� raead�� blocking�Ǵ� �۾����� UI�� �Բ� �����ϸ� �ȵǹǷ� threadǮ�� �⺻���� �۾�
	ExecutorService executorService;
	ServerSocket serverSocket;
	java.util.List<Client> connections =new Vector<Client>();
	
	//server ���� start ��ư�� ������ ����
	void startServer(){
		//��ǻ�Ͱ� ������ִ� �ִ����� threadpool�� ����
		executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		//serverSocket ����
		try{
			serverSocket =new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));
		}catch(Exception e){
			e.printStackTrace();
			if(!serverSocket.isClosed()){
				stopServer();
			}
			return;
		
		}
		
		//Threadpool�� ���� �۾�����
		Runnable runnable=new Runnable(){
			public void run(){
			Platform.runLater(()->{
			displayText("[Server start]");
			btnStartStop.setText("stop");
			});
			while(true){
				try{
					Socket socket =serverSocket.accept();
					String message="[connetion accept: "+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
					Platform.runLater(()->displayText(message));
					
					//Client�� ������ ����.
					Client client=new Client(socket);
					connections.add(client);
					Platform.runLater(()->displayText("connection number: "+connections.size()+"]"));
				}catch(Exception e){
					e.printStackTrace();
					if(!serverSocket.isClosed()){
						stopServer();
					}
					break;
				}
			}
			}
		};
		//�۾��� thread Q�� �÷�����
		executorService.submit(runnable);
		
	}
	
	void stopServer(){
		try{
			Iterator<Client> iterator=connections.iterator();
			//Client ���� ���� 
			while(iterator.hasNext()){
				Client client =iterator.next();
				client.socket.close();
				iterator.remove();
			}
			if(serverSocket!=null &&!serverSocket.isClosed()){
				serverSocket.close();
			}
			if(executorService!=null && !executorService.isShutdown()){
				executorService.shutdown();
			}
			Platform.runLater(()->{
				displayText("[Server stop]");
				btnStartStop.setText("start");
				});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	class Client {
		Socket socket;
		
		Client(Socket socket){
			this.socket =socket;
			receive();
		}
		
		//Client�� ������ message�� �޾Ƽ� ��ü client���� �ٽ� send ��.
		void receive(){
			Runnable runnable=new Runnable(){
				public void run(){
					try{
						while(true){
							byte[] byteArr =new byte[100];
							InputStream inputStream= socket.getInputStream();
							
							//Ŭ���̾�Ʈ�� ������ ����� IOEXception �߻�
							int readByteCount =inputStream.read(byteArr);
							//Ŭ���̾�Ʈ�� ���� ����� IOEXception ������ �߻����� ����
							if(readByteCount==-1){
								throw new IOException();
							}
							String message ="[process request: "+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
							Platform.runLater(()->displayText(message));
							
							String data=new String(byteArr,0,readByteCount,"UTF-8");
							for(Client client:connections){
								client.send(data);
							}
						}
					}catch(Exception e){
						try{
						e.printStackTrace();
						connections.remove(Client.this);
						String message="[Client connection fail: "+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
						Platform.runLater(()->displayText(message));
						socket.close();
						
					}catch(IOException e2){};
					}
				}	
			};
			executorService.submit(runnable);
		}
		
		void send(String data){
			Runnable runnable=new Runnable(){
				public void run(){
					try{
						byte[] byteArr =data.getBytes("UTF-8");
						OutputStream outputStream=socket.getOutputStream();
						outputStream.write(byteArr);
						outputStream.flush();
					}catch(Exception e){
						try{
							String message="[Client connection fail: "+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
							Platform.runLater(()->displayText(message));
							socket.close();
						}catch(IOException e2){}
					}
				}
			};
			executorService.submit(runnable);
		}
	}
	
	
	///////////////////////////////////////////////UI////////////////////////////////////////////////////////////////////////////////
	TextArea txtDisplay;
	Button btnStartStop;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root =new BorderPane();
		root.setPrefSize(500,300);
		
		txtDisplay=new TextArea();
		txtDisplay.setEditable(false);
		BorderPane.setMargin(txtDisplay, new Insets(0,0,2,0));
		root.setCenter(txtDisplay);
		btnStartStop =new Button("start");
		btnStartStop.setPrefHeight(30);
		btnStartStop.setMaxWidth(Double.MAX_VALUE);
		btnStartStop.setOnAction(e->{
			if(btnStartStop.getText().equals("start")){
			startServer();
		} else if(btnStartStop.getText().equals("stop")){
			stopServer();
		}
		});
		root.setBottom(btnStartStop);
		
		Scene scene=new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Server");
		primaryStage.setOnCloseRequest(event-> stopServer());
		primaryStage.show();
	}
	
	void displayText(String text){
		txtDisplay.appendText(text+"\n");
	}
	
	public static void main(String[] ar){
	launch(ar);
	}
}
