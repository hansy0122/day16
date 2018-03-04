package day16.IObaseInputOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class ChattingClient extends Application{

	Socket socket;
	void startClient(){
		Thread thread=new Thread(){
			@Override
			public void run(){
				try{
					socket=new Socket();
					socket.connect(new InetSocketAddress("localhost",5001));
					Platform.runLater(()->{
						displayText("[connection complete: "+ socket.getRemoteSocketAddress()+"]");
						btnConn.setText("Stop");
						btnSend.setDisable(false);
					});
				}catch(Exception e){
					e.printStackTrace();
					Platform.runLater(()->{
						displayText("[server connection fail]");});
					if(!socket.isClosed()){
						stopClient();
					}
					return;
				}
				receive();
			}
		};
		thread.start();
	}
	void stopClient(){
		try{
			Platform.runLater(()->{
			displayText("[disconnection");
			btnConn.setText("start");
			btnSend.setDisable(true);
			});
			
			if(socket !=null &&!socket.isClosed()){
				socket.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	void receive(){
		while(true){
			try{
				byte[] byteArr=new byte[100];
				InputStream inputStream =socket.getInputStream();
				int readByteCount =inputStream.read(byteArr);
				if(readByteCount == -1){
					throw new IOException();
				}
				String data=new String(byteArr,0,readByteCount,"UTF-8");
				Platform.runLater(()->displayText("[receive] :"+data));
			}catch(Exception e){
				e.printStackTrace();
				Platform.runLater(()->displayText("[connection fail]"));
				stopClient();
				break;
			}
		}
		
	}
	
	void send(String data){
		Thread thread =new Thread(){
			public void run(){
				try{
					byte[] byteArr=data.getBytes("UTF-8");
					OutputStream outputStream =socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
					Platform.runLater(()->displayText("[sending complete]"));
				}catch(Exception e){
					e.printStackTrace();
					Platform.runLater(()->displayText("[connection fail]"));
					stopClient();
				}
			}
		};
		thread.start();
	}
	
	////////////////////////////////////// UI ///////////////////////////////////////
	
	TextArea txtDisplay;
	TextField txtInput;
	Button btnConn,btnSend;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root=new BorderPane();
		root.setPrefSize(500, 300);
		
		txtDisplay =new TextArea();
		txtDisplay.setEditable(false);
		BorderPane.setMargin(txtDisplay, new Insets(0,0,2,0));
		root.setCenter(txtDisplay);
		
		BorderPane bottom =new BorderPane();
		txtInput=new TextField();
		txtInput.setPrefSize(60, 30);
		BorderPane.setMargin(txtInput, new Insets(0,1,1,1));
		
		btnConn=new Button("start");
		btnConn.setPrefSize(60, 30);
		btnConn.setOnAction(e->{
			if(btnConn.getText().equals("start")){
			startClient();	
			}else if(btnConn.getText().equals("stop")){
			stopClient();
			}
		});
		
		
		btnSend=new Button("send");
		btnSend.setPrefSize(60, 30);
		btnSend.setDisable(true);
		btnSend.setOnAction(e->{
			send(txtInput.getText());
		});
		
		bottom.setCenter(txtInput);
		bottom.setLeft(btnConn);
		bottom.setRight(btnSend);
		root.setBottom(bottom);
		
		Scene scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toString());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.setOnCloseRequest(event->stopClient());
		primaryStage.show();
	}
	
	void displayText(String text){
		txtDisplay.appendText(text+"\n");
	}
	
	public static void main(String ar[]){
		launch(ar);
	}

}
