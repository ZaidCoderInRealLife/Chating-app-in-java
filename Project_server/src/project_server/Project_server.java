package project_server;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;


public class Project_server {
    public static void main(String[] args) throws Exception {
        ServerSocket s_socket=new ServerSocket(2024);
        Socket socket=s_socket.accept();
        System.out.println("Connected with Client 1");
        DataInputStream input=new DataInputStream(socket.getInputStream());
        DataOutputStream output=new DataOutputStream(socket.getOutputStream());
        ServerSocket S_socket=new ServerSocket(2020);
        Socket socket1=S_socket.accept();
        System.out.println("Connnected with Client 2");
        DataInputStream input1=new DataInputStream(socket1.getInputStream());
        DataOutputStream output1=new DataOutputStream(socket1.getOutputStream());
        mainframe(socket,input,output,socket1,input1,output1);
    }
    static void mainframe(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JButton button=new JButton("Chatting");
        JButton button1=new JButton("Chatting histroy");
        JButton button2=new JButton("Open");
        JButton button3=new JButton("Sending");
        JButton button4=new JButton("Receving");
        button4.addActionListener(e->recveing_client(socket,input,output,socket1,input1,output1));
        button3.addActionListener(e->sending(socket,input,output,socket1,input1,output1));
        button3.addActionListener(e -> frame.setVisible(false));
        button2.addActionListener(e->open(socket,input,output,socket1,input1,output1));
        button2.addActionListener(e -> frame.setVisible(false));
        button.addActionListener(e ->chating(socket,input,output,socket1,input1,output1));
        button.addActionListener(e -> frame.setVisible(false));
        button1.addActionListener(e->chatting_histroy(socket,input,output,socket1,input1,output1));
        button1.addActionListener(e -> frame.setVisible(false));
        label.setText("Welcome to SERVER");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(Color.LIGHT_GRAY);
        button2.setBackground(Color.LIGHT_GRAY);
        button4.setBackground(Color.LIGHT_GRAY);
        label.setForeground(Color.white);
        button.setBounds(190, 60, 100, 50);
        button1.setBounds(190, 120, 150, 50);
        button2.setBounds(190, 180, 100, 50);
        button3.setBounds(190, 240, 100, 50);
        button4.setBounds(190, 300, 100, 50);
        button3.setBackground(Color.LIGHT_GRAY);
        button1.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 700);
        frame.setTitle("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        label.add(button4);
        label.add(button3);
        label.add(button2);
        label.add(button1);
        label.add(button);
        frame.add(label);
        frame.setVisible(true);
    }
    static void recveing_client(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        Thread receving_1 =new receving_from_client_1(input);
        Thread receving_2 =new receving_from_client_2(input1);
        receving_1.start();
        receving_2.start();
        while(receving_1.isAlive()){
            System.out.println("receving from 1");
        }
        while(receving_2.isAlive()){
            System.out.println("receving from 2");
        }
    }
    
    static void sending(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JButton button=new JButton("Text File");
        JButton button2=new JButton("Back");
        button.setBounds(190, 60, 100, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button.addActionListener(e->text_sending_client(socket,input,output,socket1,input1,output1));
        button.addActionListener(e -> frame.setVisible(false));
        button2.addActionListener(e->mainframe(socket,input,output,socket1,input1,output1));
        button2.addActionListener(e->frame.setVisible(false));
        button2.setBounds(0, 20, 100, 50);
        button2.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 700);
        frame.setTitle("Send");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        label.add(button);
        label.add(button2);
        frame.add(label);
        frame.setVisible(true);
    }
    static void text_sending_client(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        File file=new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server");
        String[] files=file.list();
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        frame.setSize(500, 700);
        frame.setTitle("Text file Sending");
        JTextArea text=new JTextArea();
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 120, 230, 300);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        JButton button2=new JButton("Send");
        button2.setBounds(300, 530, 100, 50);
        button2.setBackground(Color.LIGHT_GRAY);
        JTextArea text1=new JTextArea();
        JTextField textfield1=new JTextField();
        textfield1.setBounds(250, 460, 200, 50);
        button2.addActionListener(e->sending_txt1(output1,textfield1.getText()));
        JScrollPane scroll1=new JScrollPane(text1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setBounds(250, 120, 230, 300);
        text1.setLineWrap(true);
        text1.setWrapStyleWord(true);
        text1.setBackground(Color.white);
        text1.setVisible(true);
        text1.setBounds(250, 120, 230, 300);
        JButton button1=new JButton("Send");
        JButton button3=new JButton("Back");
        button3.addActionListener(e->mainframe(socket,input,output,socket1,input1,output1));
        button3.addActionListener(e->frame.setVisible(false));
        button3.setBounds(0, 20, 100, 50);
        button3.setBackground(Color.LIGHT_GRAY);
        JTextField textfield=new JTextField();
        button1.addActionListener(e->sending_txt(output,textfield.getText()));
        text.setBounds(0, 120, 230, 300);
        for(int i=0;i<files.length;i++){
            if((files[i].charAt(files[i].length()-1)=='t') && (files[i].charAt(files[i].length()-2)=='x') && (files[i].charAt(files[i].length()-3)=='t') && (files[i].charAt(files[i].length()-4)=='.')){
                text.append(files[i]+"\n");
                text1.append(files[i]+"\n");
            }   
        }
        textfield.setBounds(0, 460, 200, 50);
        button1.setBounds(0, 530, 100, 50);
        button1.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        frame.add(scroll);
        frame.add(scroll1);
        label.add(button1);
        label.add(button2);
        label.add(button3);
        frame.add(textfield);
        frame.add(textfield1);
        frame.add(label);
        frame.setVisible(true);
    }
    static void sending_txt1(DataOutputStream output1,String file1){
        StringBuilder file_path=new StringBuilder("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\");
        file_path.append(file1);
        String complete_file_path=file_path.toString();
        JFrame frame =new JFrame("sending to client 1");
        JTextField text=new JTextField("SEND to CLIENT 1");
        try{
            FileInputStream FIS=new FileInputStream(complete_file_path);
            byte b[]=new byte[2002];
            FIS.read(b,0,b.length);
            output1.write(b,0,b.length);
            output1.writeUTF(file1);
        }
        catch(Exception e){
            System.out.println("Sending Error");
        }
        frame.setSize(100,100);
        frame.add(text);
        frame.setVisible(true);
    }
    static void sending_txt(DataOutputStream output,String file){
        StringBuilder file_path=new StringBuilder("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\");
        file_path.append(file);
        String complete_file_path=file_path.toString();
        JFrame frame =new JFrame("sending to client 1");
        JTextField text=new JTextField("SEND to CLIENT 1");
        try{
            FileInputStream FIS=new FileInputStream(complete_file_path);
            byte b[]=new byte[2002];
            FIS.read(b,0,b.length);
            output.write(b,0,b.length);
            output.writeUTF(file);
        }
        catch(Exception e){
            System.out.println("Sending Error");
        }
        frame.setSize(100,100);
        frame.add(text);
        frame.setVisible(true);
    }
    static void open(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JButton button=new JButton("Text file");
        JButton button1=new JButton("Image file");
        button.addActionListener(e->opening_text_file(socket,input,output,socket1,input1,output1));
        button.addActionListener(e->frame.setVisible(false));
        button1.addActionListener(e->image_opening(socket,input,output,socket1,input1,output1));
        JButton button2=new JButton("Back");
        button.setBounds(190, 60, 100, 50);
        button1.setBounds(190, 120, 150, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button1.setBackground(Color.LIGHT_GRAY);
        button2.addActionListener(e->mainframe(socket,input,output,socket1,input1,output1));
        button2.addActionListener(e->frame.setVisible(false));
        button2.setBounds(0, 20, 100, 50);
        button2.setBackground(Color.LIGHT_GRAY);
        label.add(button);
        label.add(button1);
        label.add(button2);
        frame.add(label);
        frame.setSize(500, 700);
        frame.setTitle("Open");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    static void image_opening(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        JLabel label = new JLabel();
        File file=new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server");
        String[] files=file.list();
        JFrame frame=new JFrame("text opening");
        JTextArea text=new JTextArea();
        JButton button=new JButton("Back");
        JButton button1=new JButton("Open");
        JTextField textfield1=new JTextField();
        JLabel label1=new JLabel("Enter name");
        label1.setBackground(Color.black);
        label1.setBounds(2000, 400, 150, 10);
        textfield1.setBounds(0, 460, 200, 50);
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 120, 230, 300);
        for(int i=0;i<files.length;i++){
            if((files[i].charAt(files[i].length()-1)=='g') && (files[i].charAt(files[i].length()-2)=='p') && (files[i].charAt(files[i].length()-3)=='j') && (files[i].charAt(files[i].length()-4)=='.')){
                text.append(files[i]+"\n");
            }   
        }
        button1.setBounds(0, 530, 100, 50);
        button1.setBackground(Color.LIGHT_GRAY);
        button1.addActionListener(e->opening_image(textfield1.getText()));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(0, 120, 230, 300);
        button.addActionListener(e->open(socket,input,output,socket1,input1,output1));
        button.addActionListener(e->frame.setVisible(false));
        button.setBounds(0, 20, 100, 50);
        frame.setSize(500, 700);
        button.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        frame.add(scroll);
        label.add(button1);
        frame.add(textfield1);
        label.add(label1);
        label.add(button);
        frame.add(label);
        frame.setVisible(true);
    }
    static void opening_image(String file){
       StringBuilder file_path=new StringBuilder("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\");
       file_path.append(file);
       String complete_file_path=file_path.toString();
       JFrame frame =new JFrame("Frame");
       frame.setSize(700, 700);
       JLabel label=new JLabel();
       ImageIcon II=new ImageIcon(complete_file_path);
       Image image=II.getImage();
       Image imageD=image.getScaledInstance(700, 600, java.awt.Image.SCALE_SMOOTH);
       II=new ImageIcon(imageD);
       label.setIcon(II);
       frame.add(label);
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().setBackground(Color.DARK_GRAY);
       frame.setAlwaysOnTop(true);
       frame.setVisible(true);
    }
    static void opening_text_file(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        JLabel label = new JLabel();
        File file=new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server");
        String[] files=file.list();
        JFrame frame=new JFrame("text opening");
        JTextArea text=new JTextArea();
        JButton button=new JButton("Back");
        JButton button1=new JButton("Open");
        JTextField textfield1=new JTextField();
        JLabel label1=new JLabel("Enter name");
        label1.setBackground(Color.black);
        label1.setBounds(2000, 400, 150, 10);
        textfield1.setBounds(0, 460, 200, 50);
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 120, 230, 300);
        for(int i=0;i<files.length;i++){
            if((files[i].charAt(files[i].length()-1)=='t') && (files[i].charAt(files[i].length()-2)=='x') && (files[i].charAt(files[i].length()-3)=='t') && (files[i].charAt(files[i].length()-4)=='.')){
                text.append(files[i]+"\n");
            }   
        }
        button1.setBounds(0, 530, 100, 50);
        button1.setBackground(Color.LIGHT_GRAY);
        button1.addActionListener(e->opening_file(textfield1.getText(),socket,input,output,socket1,input1,output1));
        button1.addActionListener(e->frame.setVisible(false));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(0, 120, 230, 300);
        button.addActionListener(e->open(socket,input,output,socket1,input1,output1));
        button.addActionListener(e->frame.setVisible(false));
        button.setBounds(0, 20, 100, 50);
        frame.setSize(500, 700);
        button.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        frame.add(scroll);
        label.add(button1);
        frame.add(textfield1);
        label.add(label1);
        label.add(button);
        frame.add(label);
        frame.setVisible(true);
    }
    static void opening_file(String filename,Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        StringBuilder file_path=new StringBuilder("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\");
        file_path.append(filename);
        String data,file=file_path.toString();
        JFrame frame=new JFrame(filename);
        frame.setSize(500, 700);
        JTextArea text=new JTextArea();
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50, 120, 300, 300);
        try{
            data=new Scanner(new File(file)).useDelimiter("\\A").next();
            text.setText(data);
        }
        catch(Exception e){
            System.out.println("File reading ERROR");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        JLabel label=new JLabel();
        JButton button=new JButton("Back");
        button.addActionListener(e->opening_text_file(socket,input,output,socket1,input1,output1));
        button.addActionListener(e->frame.setVisible(false));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBounds(20, 20, 100, 50);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(50, 120, 300, 300);
        frame.add(scroll);
        label.add(button);
        frame.add(label);
        frame.setVisible(true);
    }
    static void chating(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        JLabel label1=new JLabel("Reciving frome Client  1");
        label1.setBackground(Color.black);
        label1.setBounds(50, 100, 150, 10);
        JLabel lab=new JLabel("Reciving frome Client 2");
        lab.setBackground(Color.black);
        lab.setBounds(300, 100, 150, 10);
        JFrame frame=new JFrame("Server Chatting");
        JLabel label = new JLabel();
        JPanel panel=new JPanel();
        JButton button=new JButton("Back");
        JButton button1=new JButton("Send");
        JButton button2=new JButton("Send");
        JTextArea text=new JTextArea();
        JTextArea text1=new JTextArea();
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 120, 230, 300);
        JScrollPane scroll1=new JScrollPane(text1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setBounds(250, 120, 230, 300);
        JTextField textfield=new JTextField();
        textfield.setBounds(0, 460, 200, 50);
        JTextField textfield1=new JTextField();
        textfield1.setBounds(250, 460, 200, 50);
        button1.setBounds(0, 530, 100, 50);
        button1.setBackground(Color.LIGHT_GRAY);
        button2.setBounds(300, 530, 100, 50);
        button2.setBackground(Color.LIGHT_GRAY);
        Thread read=new reading(socket,input,text);
        Thread read1=new reading1(socket1,input1,text1);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(0, 120, 230, 300);
        text1.setLineWrap(true);
        text1.setWrapStyleWord(true);
        text1.setBackground(Color.white);
        text1.setVisible(true);
        text1.setBounds(250, 120, 230, 300);
        button.addActionListener(e->mainframe(socket,input,output,socket1,input1,output1));
        button.addActionListener(e->frame.setVisible(false));
        button.setBounds(0, 20, 100, 50);
        button.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 700);
        button1.addActionListener(e->sending(output,textfield.getText()));
        button2.addActionListener(e->sending1(output1,textfield1.getText()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        frame.add(textfield1);
        frame.add(textfield);
        frame.add(button1);
        frame.add(button2);
        frame.add(scroll1);
        frame.add(scroll);
        label.add(lab);
        label.add(label1);
        frame.add(label);
        label.add(button);
        frame.setVisible(true);
        read.start();
        read1.start();
    }
    static void chatting_histroy(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
       JFrame frame=new JFrame("Chatting Histroy");
       JButton button=new JButton("Client 1");
       JButton button1=new JButton("Client 2");
       JButton button2=new JButton("Back");
       JLabel label=new JLabel();
       button.addActionListener(e->client_1_chatting_histroy(socket,input,output,socket1,input1,output1));
       button.addActionListener(e->frame.setVisible(false));
       button1.addActionListener(e->client_2_chatting_histroy(socket,input,output,socket1,input1,output1));
       button1.addActionListener(e->frame.setVisible(false));
       button.setBackground(Color.LIGHT_GRAY);
       button.setBounds(100, 100, 100, 50);
       button1.setBackground(Color.LIGHT_GRAY);
       button1.setBounds(100, 200, 100, 50);
       frame.setSize(500, 700); 
       button2.addActionListener(e->mainframe(socket,input,output,socket1,input1,output1));
       button2.addActionListener(e->frame.setVisible(false));
       button2.setBounds(0, 20, 100, 50);
       button2.setBackground(Color.LIGHT_GRAY);
       label.add(button2);
       label.add(button1);
       label.add(button);
       frame.add(label);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().setBackground(Color.DARK_GRAY);
       frame.setAlwaysOnTop(true);
       frame.setVisible(true);
    }
    static void client_2_chatting_histroy(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        String data;
        JFrame frame=new JFrame("Chatting Histroy for CLIENT 2");
        frame.setSize(500, 700);
        JTextArea text=new JTextArea();
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50, 120, 300, 300);
        try{
            data=new Scanner(new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\Chatting_histroy1.txt")).useDelimiter("\\A").next();
            text.setText(data);
        }
        catch(Exception e){
            System.out.println("File reading ERROR");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        JLabel label=new JLabel();
        JButton button=new JButton("Back");
        button.addActionListener(e->chatting_histroy(socket,input,output,socket1,input1,output1));
        button.addActionListener(e->frame.setVisible(false));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBounds(20, 20, 100, 50);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(50, 120, 300, 300);
        frame.add(scroll);
        label.add(button);
        frame.add(label);
        frame.setVisible(true);
    }
    static void client_1_chatting_histroy(Socket socket,DataInputStream input,DataOutputStream output,Socket socket1,DataInputStream input1,DataOutputStream output1){
        String data;
        JFrame frame=new JFrame("Chatting Histroy for CLIENT 1");
        frame.setSize(500, 700);
        JTextArea text=new JTextArea();
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50, 120, 300, 300);
        try{
            data=new Scanner(new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\Chatting_histroy.txt")).useDelimiter("\\A").next();
            text.setText(data);
        }
        catch(Exception e){
            System.out.println("File reading ERROR");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        JLabel label=new JLabel();
        JButton button=new JButton("Back");
        button.addActionListener(e->chatting_histroy(socket,input,output,socket1,input1,output1));
        button.addActionListener(e->frame.setVisible(false));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBounds(20, 20, 100, 50);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(50, 120, 300, 300);
        frame.add(scroll);
        label.add(button);
        frame.add(label);
        frame.setVisible(true);
    }
    static void sending(DataOutputStream output,String text){
        try{
            FileWriter writer=new FileWriter("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\Chatting_histroy.txt",true);
            output.writeUTF(text);
            writer.write("Server : "+text+"\n");
            writer.close();
        }
        catch(Exception e){
            System.out.println("Sending ERROR");
        }
        
    }
    static void sending1(DataOutputStream output1,String text1){
        try{
            FileWriter writer=new FileWriter("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\Chatting_histroy1.txt",true);
            output1.writeUTF(text1);
            writer.write("Server : "+text1+"\n");
            writer.close();
        }
        catch(Exception e){
            System.out.println("Sending ERROR");
        }
        
    }
    static class reading1 extends Thread{
        Socket socket1;
        DataInputStream input1;
        String read="";
        JTextArea text1;
        reading1(Socket socket1,DataInputStream input1,JTextArea text1){
            this.socket1=socket1;
            this.input1=input1;
            this.text1=text1;
        }
        @Override
        public void run(){    
            try{
                while(true){
                    read=input1.readUTF();
                    text1.append(read+"\n");
                    Thread.sleep(500);
               }
                  
            }
            catch(Exception e){
                System.out.println("Reading error");
            }
        }
    }
    
    static class reading extends Thread{
        Socket socket;
        DataInputStream input;
        String read="";
        JTextArea text;
        reading(Socket socket,DataInputStream input,JTextArea text){
            this.socket=socket;
            this.input=input;
            this.text=text;
        }
        @Override
        public void run(){    
            try{
                while(true){
                    read=input.readUTF();
                    text.append(read+"\n");
                    Thread.sleep(500);
                }
                 
            }
            catch(Exception e){
                System.out.println("Reading error");
            }
        }
    }
    static class receving_from_client_1 extends Thread{
        DataInputStream input;
        receving_from_client_1(DataInputStream input){
            this.input=input;
        }
        @Override
        public void run(){
            try{
                byte[] b=new byte[2002];
            input.read(b,0,b.length);
            String name=input.readUTF();
            FileOutputStream FOS=new FileOutputStream("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\"+name);
            FOS.write(b,0,b.length);
            Thread.sleep(b.length);
            }
            catch(Exception e){
                System.out.println("Receving ERROR");
            }
        }
    }
    static class receving_from_client_2 extends Thread{
        DataInputStream input1;
        receving_from_client_2(DataInputStream input1){
            this.input1=input1;
        }
        @Override
        public void run(){
            try{
                byte[] b=new byte[2002];
            input1.read(b,0,b.length);
            String name=input1.readUTF();
            FileOutputStream FOS=new FileOutputStream("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_server\\src\\project_server\\"+name);
            FOS.write(b,0,b.length);
            Thread.sleep(b.length);
            }
            catch(Exception e){
                System.out.println("Receving ERROR");
            }
        }
    }
}