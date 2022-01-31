
package project_client;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.net.*;
import java.util.*;


public class Project_client {

    
    public static void main(String[] args) throws Exception{
        Socket socket=new Socket("localhost",2024);
        DataInputStream input=new DataInputStream(socket.getInputStream());
        
        DataOutputStream output=new DataOutputStream(socket.getOutputStream());
        
        mainframe(socket,input,output);
    }
    static void mainframe(Socket socket,DataInputStream input,DataOutputStream output){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JButton button=new JButton();
        JButton button1=new JButton("Chatting histroy of Client 1");
        JButton button2 =new JButton("Receving");
        JButton button3=new JButton("Open");
        JButton button4=new JButton("Sending");
        button3.addActionListener(e->open(socket,input,output));
        button3.addActionListener(e -> frame.setVisible(false));
        button4.addActionListener(e -> frame.setVisible(false));
        button4.addActionListener(e->sending_txt(socket,input,output));
        button3.setBackground(Color.LIGHT_GRAY);
        button3.setBounds(190, 240, 100, 50);
        button4.setBackground(Color.LIGHT_GRAY);
        button4.setBounds(190, 300, 100, 50);
        button2.addActionListener(e ->receving(socket,input,output));
        button2.addActionListener(e -> frame.setVisible(false));
        button2.setBounds(190, 180, 100, 50);
        button.setText("Chatting Client 1");
        button.addActionListener(e ->chating(socket,input,output));
        button.addActionListener(e -> frame.setVisible(false));
        button1.addActionListener(e->chatting_histroy(socket,input,output));
        button1.addActionListener(e -> frame.setVisible(false));
        label.setText("Welcome to Client 1");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        button.setBackground(Color.LIGHT_GRAY);
        label.setForeground(Color.white);
        button.setBounds(190, 60, 100, 50);
        button1.setBounds(190, 120, 150, 50);
        button1.setBackground(Color.LIGHT_GRAY);
        button2.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 700);
        frame.setTitle("Client 1");
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
    static void sending_txt(Socket socket,DataInputStream input,DataOutputStream output){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JButton button=new JButton("Text File");
        
        JButton button2=new JButton("Back");
        button.setBounds(190, 60, 100, 50);
        
        button.setBackground(Color.LIGHT_GRAY);
        
        button.addActionListener(e->text_sending_client(socket,input,output));
        button.addActionListener(e -> frame.setVisible(false));
        button2.addActionListener(e->mainframe(socket,input,output));
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
    static void text_sending_client(Socket socket,DataInputStream input,DataOutputStream output){
        File file=new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_client\\src\\project_client");
        String[] files=file.list();
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        frame.setSize(500, 700);
        frame.setTitle("Text file Sending from Client 1");
        JTextArea text=new JTextArea();
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 120, 230, 300);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        JButton button1=new JButton("Send");
        JButton button3=new JButton("Back");
        button3.addActionListener(e->mainframe(socket,input,output));
        button3.addActionListener(e->frame.setVisible(false));
        button3.setBounds(0, 20, 100, 50);
        button3.setBackground(Color.LIGHT_GRAY);
        JTextField textfield=new JTextField();
        button1.addActionListener(e->sending_txt(output,textfield.getText()));
        text.setBounds(0, 120, 230, 300);
        for(int i=0;i<files.length;i++){
            if((files[i].charAt(files[i].length()-1)=='t') && (files[i].charAt(files[i].length()-2)=='x') && (files[i].charAt(files[i].length()-3)=='t') && (files[i].charAt(files[i].length()-4)=='.')){
                text.append(files[i]+"\n");
                
            }   
        }
        textfield.setBounds(0, 460, 200, 50);
        button1.setBounds(0, 530, 100, 50);
        button1.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        frame.add(scroll);
        label.add(button1);
        label.add(button3);
        frame.add(textfield);
        frame.add(label);
        frame.setVisible(true);
    }
    static void sending_txt(DataOutputStream output,String file){
        StringBuilder file_path=new StringBuilder("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_client\\src\\project_client\\");
        file_path.append(file);
        String complete_file_path=file_path.toString();
        JFrame frame =new JFrame("sending from client 1");
        JTextField text=new JTextField("SEND from CLIENT 1");
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
    static void open(Socket socket,DataInputStream input,DataOutputStream output){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JButton button=new JButton("Text file");
        JButton button1=new JButton("Image file");
        button.addActionListener(e->opening_text_file(socket,input,output));
        button.addActionListener(e->frame.setVisible(false));
        button1.addActionListener(e->image_opening(socket,input,output));
        JButton button2=new JButton("Back");
        button.setBounds(190, 60, 100, 50);
        button1.setBounds(190, 120, 150, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button1.setBackground(Color.LIGHT_GRAY);
        button2.addActionListener(e->mainframe(socket,input,output));
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
    static void opening_text_file(Socket socket,DataInputStream input,DataOutputStream output){
        JLabel label = new JLabel();
        File file=new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_client\\src\\project_client");
        String[] files=file.list();
        JFrame frame=new JFrame("Text opening of Client 1");
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
        button1.addActionListener(e->opening_file(textfield1.getText(),socket,input,output));
        button1.addActionListener(e->frame.setVisible(false));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(0, 120, 230, 300);
        button.addActionListener(e->open(socket,input,output));
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
    static void opening_file(String filename,Socket socket,DataInputStream input,DataOutputStream output){
        StringBuilder file_path=new StringBuilder("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_client\\src\\project_client\\");
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
        button.addActionListener(e->opening_text_file(socket,input,output));
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
    static void image_opening(Socket socket,DataInputStream input,DataOutputStream output){
        JLabel label = new JLabel();
        File file=new File("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_client\\src\\project_client");
        String[] files=file.list();
        JFrame frame=new JFrame("Text opening of Client 1");
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
        button.addActionListener(e->open(socket,input,output));
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
       StringBuilder file_path=new StringBuilder("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_client\\src\\project_client\\");
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
    static void receving(Socket socket,DataInputStream input,DataOutputStream output){
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JButton button=new JButton();
        JButton button1=new JButton("Text file");
        JButton button2=new JButton("Image file");
        button.setText("Back");
        button.addActionListener(e->mainframe(socket,input,output));
        button.addActionListener(e->frame.setVisible(false));
        button1.addActionListener(e->receving_txt(input));
        button.setBounds(20, 20, 100, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button1.setBounds(190, 60, 100, 50);
        button2.setBounds(190, 120, 150, 50);
        button2.setBackground(Color.LIGHT_GRAY);
        button1.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 700);
        frame.setTitle("Client 1 Chatting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        label.add(button1);
        label.add(button2);
        label.add(button);
        frame.add(label);
        frame.setVisible(true);
    }
    static void receving_txt(DataInputStream input){
        JFrame frame=new JFrame("text receving");
        JTextField textfield=new JTextField("RECEVIED");
        
        try{
            byte[] b=new byte[2002];
            input.read(b,0,b.length);
            String name=input.readUTF();
            FileOutputStream FOS=new FileOutputStream("C:\\Users\\DeathBlade\\Documents\\NetBeansProjects\\Project_client\\src\\project_client\\"+name);
            FOS.write(b,0,b.length);
        }
        catch(Exception e){
            System.out.println("Receving ERROR");
        }
        frame.setSize(100,100);
        frame.add(textfield);
        
        frame.setVisible(true);
    }
    static void chating(Socket socket,DataInputStream input,DataOutputStream output){
        JLabel label1=new JLabel();
        label1.setText("RECEVING MESSEGES");
        label1.setBackground(Color.black);
        label1.setBounds(50, 100, 150, 10);
        JFrame frame=new JFrame();
        JLabel label = new JLabel();
        JPanel panel=new JPanel();
        JButton button=new JButton();
        JButton button1=new JButton("Send");
        final JTextArea text=new JTextArea();
        JScrollPane scroll=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50, 120, 300, 300);
        JTextField textfield=new JTextField();
        textfield.setBounds(50, 460, 300, 50);
        button1.setBounds(50, 530, 100, 50);
        button1.setBackground(Color.LIGHT_GRAY);
        Thread read=new reading(socket,input,text);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBackground(Color.white);
        text.setVisible(true);
        text.setBounds(50, 120, 300, 300);
        button.setText("Back");
        button.addActionListener(e->mainframe(socket,input,output));
        button.addActionListener(e->frame.setVisible(false));
        button.setBounds(20, 20, 100, 50);
        button.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 700);
        frame.setTitle("Client 1 Chatting");
        button1.addActionListener(e->sending(output,textfield.getText()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setAlwaysOnTop(true);
        
        
        frame.add(textfield);
        frame.add(button1);
        
        frame.add(scroll);
        label.add(label1);
        frame.add(label);
        label.add(button);
        frame.setVisible(true);
        read.start();
    }
    static void chatting_histroy(Socket socket,DataInputStream input,DataOutputStream output){
        String data;
        JFrame frame=new JFrame("Chatting Histroy of Client 1");
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
        button.addActionListener(e->mainframe(socket,input,output));
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
            writer.write("Client 1 : "+text+"\n");
            writer.close();
            
        }
        catch(Exception e){
            System.out.println("Sending ERROR");
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
                while(!"end".equals(read)){
                    read=input.readUTF();
                    text.append(read+"\n");
                }
                  
            }
            catch(Exception e){
                System.out.println("Reading error");
            }
        }
    }
}