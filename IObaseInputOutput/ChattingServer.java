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
//Application javaFX에 있음.
public class ChattingServer extends Application {
	//Threadpool을 위함
	//socket의 accept나 connect 혹은 inputstream의 raead등 blocking되는 작업들을 UI와 함께 구동하면 안되므로 thread풀을 기본토대로 작업
	ExecutorService executorService;
	ServerSocket serverSocket;
	java.util.List<Client> connections =new Vector<Client>();
	
	//server 에서 start 버튼을 눌리면 구동
	void startServer(){
		//컴퓨터가 만들수있는 최대한의 threadpool을 생성
		executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		//serverSocket 생성
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
		
		//Threadpool에 담을 작업생성
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
					
					//Client의 정보를 담음.
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
		//작업을 thread Q에 올려놓음
		executorService.submit(runnable);
		
	}
	
	void stopServer(){
		try{
			Iterator<Client> iterator=connections.iterator();
			//Client 정보 제거 
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
		
		//Client가 보내는 message를 받아서 전체 client에게 다시 send 함.
		void receive(){
			Runnable runnable=new Runnable(){
				public void run(){
					try{
						while(true){
							byte[] byteArr =new byte[100];
							InputStream inputStream= socket.getInputStream();
							
							//클라이언트가 비정상 종료시 IOEXception 발생
							int readByteCount =inputStream.read(byteArr);
							//클라이언트가 정상 종료시 IOEXception 강제로 발생시켜 종료
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
