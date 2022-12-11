import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor {
    public static void main(String[] args) {
        new FrameClass();
    }
}

class FrameClass implements ActionListener {

    Border border = BorderFactory.createLineBorder(Color.black,2);
    JTextArea jTextArea;
    JMenuItem neW,open,save,print,cut,copy,paste,close;
    JFrame jFrame;
    JMenu menu1,menu2,menu3;
    JMenuBar menuBar;
    public FrameClass(){
        menuBar=new JMenuBar();

        menu1 = new JMenu("FILE");
        neW = new JMenuItem("New");neW.addActionListener(this);
        open = new JMenuItem("Open");open.addActionListener(this);
        save = new JMenuItem("Save");save.addActionListener(this);
        print = new JMenuItem("Print");print.addActionListener(this);
        menu1.add(neW); menu1.add(open);menu1.add(save);menu1.add(print);
        menuBar.add(menu1);

        menu2 = new JMenu("EDIT");
        cut = new JMenuItem("Cut");cut.addActionListener(this);
        copy = new JMenuItem("Copy");copy.addActionListener(this);
        paste = new JMenuItem("Paste");paste.addActionListener(this);
        menu2.add(cut); menu2.add(copy);menu2.add(paste);
        menuBar.add(menu2);

        menu3= new JMenu("CLOSE");
        close = new JMenuItem("Close");close.addActionListener(this);
        menu3.add(close);
        menuBar.add(menu3);

        jTextArea = new JTextArea();
        jTextArea.setBounds(0,0,500,500);
        //jTextArea.setBorder(border);
        
        jFrame = new JFrame("Text Editor");
        jFrame.setBounds(0,0,500,500);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(true);
        jFrame.setJMenuBar(menuBar);
        jFrame.add(jTextArea);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if(action.equals("Open")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1 = "", s2 = "";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine() +"\n";
                    while ((s1 = bufferedReader.readLine()) != null) {
                        s2 += s1 + "\n";
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(action.equals("New")){
            jTextArea.setText("");
        }
        else if(action.equals("Save")){
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer=new BufferedWriter(new FileWriter(file,false));
                    writer.write((jTextArea.getText()));
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(action.equals("Print")){
            try{
                jTextArea.print();
            }
            catch (PrinterException ex){
                throw new RuntimeException(ex);
            }
        }
        else if(action.equals("Cut")){
            jTextArea.cut();
        }
        else if(action.equals("Copy")){
            jTextArea.copy();
        }
        else if(action.equals("Paste")){
            jTextArea.paste();
        }
        else if(action.equals("Close"))
            jFrame.dispose();

    }
}
