/// This application  code is developed on JDK 1.8
import java.util.*;
import javax.swing. *;
import java.awt.*;
import java.awt.print.*;
import javax.print.attribute.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.UIManager;
import java.io.*;
public class notepad extends Thread implements ActionListener,ItemListener,ChangeListener
{

        JDialog jd;
        static int data,size,textRed,textGreen,textBlue,bgRed,bgGreen,bgBlue;
        static String stringSelect;
        static File filen,fileFont;
        static JTextArea dataArea;
        static int intFontList,intFontSize,intFontStyle;
        JFrame frame,subframe;
        static JColorChooser jc1,jc2;
        JFileChooser fc;
        JScrollPane scrollPane;
        JMenuBar menuBar;
        JMenu file,edit,format,view,help;
        JMenuItem fileNew,fileOpen,fileSave,fileSaveAs,filePageSetup,filePrint,fileExit;
        JMenuItem editUndo,editCut,editCopy,editPaste,editDelete;
        JMenuItem editFind,editFindNext,editReplace,editGoTO,editSelectAll,formatWordWrap,formatFont;
        JPanel contentPanel,jl,fonts,subend,left,right,centerPanel1,centerPanel2,centerPanel3;
        JButton close,ok,cancel;
        JLabel fontlab,colorLabel,colorLabel1,colorLabel2;
        JCheckBoxMenuItem cbMenuItem;
        static Color newFontColor,newBgColor,c1,c2;
        static JComboBox <String>fontlist,fontstyles,fontsize;
        static PrinterJob pj = PrinterJob.getPrinterJob();
        /// here run function is used for threads in this program. that will process in background to run another process in background 
        public int getLine()
        {
          return 0;
        }
        public void getTextLine(int lineNum)
        {

        }
        public void run()
        {
            fontDialog();
        }
        /// threads ends here 

        /// printing dialog 
        private void printFiles()
        {
          
        }

        /// printing dialog ends here 
        public void fontDialog()
        {
          subframe=new JFrame();
            jd = new JDialog(subframe, "Font", true);
            //jd.setLayout(new BoxLayout());
            subframe.setVisible(false);
            jl=new JPanel();
            fonts=new JPanel();
            subend = new JPanel();
            left = new JPanel();
            right = new JPanel();
            centerPanel1 = new JPanel();
            centerPanel2 = new JPanel();
            centerPanel3 = new JPanel();
            JTabbedPane jtb = new JTabbedPane();
            jtb.addTab("Font ",centerPanel1);
            jtb.addTab("Text Color ",centerPanel2);
            jtb.addTab("BackGround ",centerPanel3);
            fonts.add(jtb);
            GraphicsEnvironment e=GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font[] systemfonts=e.getAllFonts();
            String[] fontname=new String[systemfonts.length];
            int i=0;
            for(Font f:systemfonts)
            {
              fontname[i]=f.getFontName();
              i++;
            }
            fontlist=new JComboBox<String>(fontname);
            fontlist.setPreferredSize(new Dimension(150,20));
            centerPanel1.add(fontlist);
            String styleString[] = {"Regular","Italic","Bold","Bold Italic"};
            fontstyles = new JComboBox <String> (styleString);
            fontstyles.setPreferredSize(new Dimension(150,20));
            centerPanel1.add(fontstyles);
            String[] sizeString = {"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"};
            fontsize = new JComboBox <String> (sizeString);
            fontsize.setPreferredSize(new Dimension(150,20));
            centerPanel1.add(fontsize);

            fontlist.setSelectedIndex(intFontList);
            fontstyles.setSelectedIndex(intFontStyle);
            fontsize.setSelectedIndex(intFontSize);
              ///centerPanel2.setLayout(new GridLayout(1,2));
              /// Text  color chooser
            colorLabel1 = new JLabel("Text ",JLabel.CENTER);
            colorLabel1.setForeground(newFontColor);//setes foreground of label
            colorLabel1.setBackground(newBgColor);//sets background of label
            colorLabel1.setOpaque(true);
            Font f = new Font ("SansSerif",Font.BOLD, 24);
            colorLabel1.setFont(f); // Sets font of label
            colorLabel1.setPreferredSize(new Dimension(100,65));
            JPanel bannerPanel = new JPanel(new BorderLayout());
            bannerPanel.add(colorLabel1, BorderLayout.CENTER);
            bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));
            jc1=new JColorChooser(c2);
            jc1.getSelectionModel().addChangeListener(this);
            jc1.setBorder(BorderFactory.createTitledBorder("choose Text color"));
            centerPanel2.add(bannerPanel);
            centerPanel2.add(jc1);
              //
              /// BackGround  color chooser
            colorLabel2 = new JLabel("Screen ",JLabel.CENTER);
            colorLabel2.setForeground(newFontColor);//setes foreground of label
            colorLabel2.setBackground(newBgColor);//sets background of label
            colorLabel2.setOpaque(true);
            Font f1 = new Font ("SansSerif",Font.BOLD, 24);
            colorLabel2.setFont(f1); // Sets font of label
            colorLabel2.setPreferredSize(new Dimension(100,65));
            JPanel bannerPanel1 = new JPanel(new BorderLayout());
            bannerPanel1.add(colorLabel2, BorderLayout.CENTER);
            bannerPanel1.setBorder(BorderFactory.createTitledBorder("Banner"));
            jc2=new JColorChooser(c1);
            jc2.getSelectionModel().addChangeListener(this);
            jc2.setBorder(BorderFactory.createTitledBorder("choose BackGround color"));
            centerPanel3.add(bannerPanel1);
            centerPanel3.add(jc2);
             //

               //jl.setLayout(fw);

            ok = new JButton("OK");
            ok.addActionListener(this);
            cancel = new JButton("Cancel");
            cancel.addActionListener(this);
            subend.add(ok);
            subend.add(cancel);
            jd.setLocation(100,100);
            jd.add(fonts,BorderLayout.CENTER);
            jd.add(subend,BorderLayout.PAGE_END);
        }
        public void stateChanged(ChangeEvent ce)
        {
          newFontColor = jc1.getColor();
          newBgColor = jc2.getColor();
          colorLabel1.setForeground(newFontColor);
          colorLabel2.setBackground(newBgColor);
        }
        public void actionPerformed (ActionEvent ae)
        {
          fc = new JFileChooser();
          if (ae.getSource()==fileNew)
          {
            if (!(frame.getTitle().equals("Notepad - Document1")))
            {
              try
              {
                FileInputStream fin = new FileInputStream(filen);
                size=fin.available();
                byte b1[]=new byte[size];
                fin.read(b1);
                String datas=new String(b1);
                String  dataString1 = dataArea.getText();
                if (datas.equals(dataString1))
                {
                  dataArea.setText("");
                  frame.setTitle("Notepad - Document1");
                }
                else
                {
                  int x = JOptionPane.showConfirmDialog(null,"Do you want to Save file ?");
                  if (x == 0)
                  {
                    try
                    {
                      FileOutputStream f1 = new FileOutputStream(filen);
                      String  dataString = dataArea.getText();
                      byte b[] = dataString.getBytes();
                      f1.write(b);
                      f1.close();
                      dataArea.setText("");
                      frame.setTitle("Notepad - Document1");
                    }catch (Exception e){}
                  }
                  else if (x == JOptionPane.NO_OPTION)
                  {
                    dataArea.setText("");
                    frame.setTitle("Notepad - Document1");
                  }
                  else
                  {

                  }

                }
              }
              catch (Exception aao){}
            }
            else
            {
              String dataString1 = dataArea.getText();
              if (!(dataString1.equals("")))
              {
                int x = JOptionPane.showConfirmDialog(null,"Do you want to Save file ?");
                if (x == 0)
                {
                  int returnVal = fc.showSaveDialog(frame);
                  if (returnVal == JFileChooser.APPROVE_OPTION)
                  {
                    filen = fc.getSelectedFile();
                    frame.setTitle(filen.getName());
                    try
                    {
                      FileOutputStream f1 = new FileOutputStream(filen);
                      String  dataString = dataArea.getText();
                      byte b[] = dataString.getBytes();
                      f1.write(b);
                      f1.close();
                      dataArea.setText("");
                      frame.setTitle("Notepad - Document1");
                    }
                    catch (Exception e)
                    {
                    }
                  }
                }
                else if (x == JOptionPane.NO_OPTION)
                {
                  dataArea.setText("");
                  frame.setTitle("Notepad - Document1");
                }
                else
                {

                }
              }
              else
              {

              }
            }
          }
          else if (ae.getSource() == fileSave)
          {
            if(frame.getTitle().equals("Notepad - Document1"))
            {
              int returnVal = fc.showSaveDialog(frame);
              if (returnVal == JFileChooser.APPROVE_OPTION)
              {
                filen = fc.getSelectedFile();
                //path=filen.getAbsolutePath();
                frame.setTitle(filen.getName());
                try
                {
                  FileOutputStream f1 = new FileOutputStream(filen);
                  String  dataString = dataArea.getText();
                  byte b[] = dataString.getBytes();
                  f1.write(b);
                  f1.close();
                }
                catch (Exception e){}
              }
            }
            else
            {
              try
              {
                FileOutputStream f1 = new FileOutputStream(filen);
                String  dataString = dataArea.getText();
                byte b[] = dataString.getBytes();
                f1.write(b);
                f1.close();
              }
              catch (Exception e){}
            }
          }
          else if (ae.getSource() == fileOpen)
          {
            if(!(frame.getTitle().equals("Notepad - Document1")))
            {
              try
              {
                FileInputStream fin1 = new FileInputStream(filen);
                size=fin1.available();
                byte b1[]=new byte[size];
                fin1.read(b1);
                String datas1=new String(b1);
                String  dataString1 = dataArea.getText();
                if (datas1.equals(dataString1))
                {
                  int returnVal = fc.showOpenDialog(frame);
                  if (returnVal == JFileChooser.APPROVE_OPTION)
                  {
                    try
                    {
                      filen = fc.getSelectedFile();
                      frame.setTitle(filen.getName());
                      FileInputStream fin = new FileInputStream(filen);
                      size=fin.available();
                      byte b[]=new byte[size];
                      fin.read(b);
                      String datas=new String(b);
                      dataArea.setText(datas);
                      fin.close();
                    }
                    catch(Exception e){}
                  }
                }
                else
                {
                  int x = JOptionPane.showConfirmDialog(null,"Do you want to save file ?");
                  if (x == 0)
                  {
                    // saving a file
                    try
                    {
                      FileOutputStream f1 = new FileOutputStream(filen);
                      String  dataString = dataArea.getText();
                      byte b[] = dataString.getBytes();
                      f1.write(b);
                      f1.close();
                    }
                    catch (Exception e){}
                    // opening a file
                    int returnVal = fc.showOpenDialog(frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                    {
                      try
                      {
                        filen = fc.getSelectedFile();
                        frame.setTitle(filen.getName());
                        FileInputStream fin = new FileInputStream(filen);
                        size=fin.available();
                        byte b[]=new byte[size];
                        fin.read(b);
                        String datas=new String(b);
                        dataArea.setText(datas);
                        fin.close();
                      }
                      catch(Exception eee){}
                    }
                  }
                  else if (x == JOptionPane.NO_OPTION)
                  {
                    int returnVal = fc.showOpenDialog(frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION)
                    {
                      try
                      {
                        filen = fc.getSelectedFile();
                        frame.setTitle(filen.getName());
                        FileInputStream fin = new FileInputStream(filen);
                        size=fin.available();
                        byte b[]=new byte[size];
                        fin.read(b);
                        String datas=new String(b);
                        dataArea.setText(datas);
                        fin.close();
                      }
                      catch(Exception eee){}
                    }
                  }
                  else
                  {
                  }
                }
              }
              catch(Exception R){}
            }
            else
            {
              if (dataArea.getText().equals(""))
              {
                int returnVal = fc.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                  try
                  {
                    filen = fc.getSelectedFile();
                    frame.setTitle(filen.getName());
                    FileInputStream fin = new FileInputStream(filen);
                    size=fin.available();
                    byte b[]=new byte[size];
                    fin.read(b);
                    String datas=new String(b);
                    dataArea.setText(datas);
                    fin.close();
                  }
                  catch(Exception eee){}
                }
              }
              else
              {
                int x = JOptionPane.showConfirmDialog(null,"Do you want to save file ?");
                if (x == 0)
                {
                  // open save dilog box
                  int returnVal = fc.showSaveDialog(frame);
                  if (returnVal == JFileChooser.APPROVE_OPTION)
                  {
                    File file = fc.getSelectedFile();
                    frame.setTitle(file.getName());
                    try
                    {
                      FileOutputStream f1 = new FileOutputStream(file);
                      String  dataString = dataArea.getText();
                      byte b[] = dataString.getBytes();
                      f1.write(b);
                      f1.close();
                    }
                    catch (Exception e){}
                  }
                  int returnVal1 = fc.showOpenDialog(frame);
                  if (returnVal1 == JFileChooser.APPROVE_OPTION)
                  {
                    try
                    {
                      filen = fc.getSelectedFile();
                      frame.setTitle(filen.getName());
                      FileInputStream fin = new FileInputStream(filen);
                      size=fin.available();
                      byte b[]=new byte[size];
                      fin.read(b);
                      String datas=new String(b);
                      dataArea.setText(datas);
                      fin.close();
                    }
                    catch(Exception eee){}
                  }
                }
                else if(x == JOptionPane.NO_OPTION)
                {
                  int returnVal1 = fc.showOpenDialog(frame);
                  if (returnVal1 == JFileChooser.APPROVE_OPTION)
                  {
                    try
                    {
                      filen = fc.getSelectedFile();
                      frame.setTitle(filen.getName());
                      FileInputStream fin = new FileInputStream(filen);
                      size=fin.available();
                      byte b[]=new byte[size];
                      fin.read(b);
                      String datas=new String(b);
                      dataArea.setText(datas);
                      fin.close();
                    }
                    catch(Exception eee){}
                  }
                }
                else
                {

                }
             }
          }
        }
        else if (ae.getSource() == fileSaveAs)
        {
          int returnVal = fc.showSaveDialog(frame);
          if (returnVal == JFileChooser.APPROVE_OPTION)
          {
            File file = fc.getSelectedFile();
            frame.setTitle(file.getName());
            try
            {
              FileOutputStream f1 = new FileOutputStream(file);
              String  dataString = dataArea.getText();
              byte b[] = dataString.getBytes();
              f1.write(b);
              f1.close();
            }
            catch (Exception e){}
          }
        }
        else if (ae.getSource()==fileExit)
        {
          if (!(frame.getTitle().equals("Notepad - Document1")))
          {
            try
            {
              FileInputStream fin = new FileInputStream(filen);
              size=fin.available();
              byte b1[]=new byte[size];
              fin.read(b1);
              String datas=new String(b1);
              String  dataString1 = dataArea.getText();
              if (datas.equals(dataString1))
              {
                System.exit(0);
              }
              else
              {
                int x = JOptionPane.showConfirmDialog(null,"Do you want to Save file ?");
                if (x == 0)
                {
                  try
                  {
                    FileOutputStream f1 = new FileOutputStream(filen);
                    String  dataString = dataArea.getText();
                    byte b[] = dataString.getBytes();
                    f1.write(b);
                    f1.close();
                    System.exit(0);
                  }
                  catch (Exception e){}
                }
                else if (x == JOptionPane.NO_OPTION)
                {
                  System.exit(0);
                }
                else
                {
                }
              }
            }
            catch (Exception aao){}
        }
        else
        {
          String dataString1 = dataArea.getText();
          if (!(dataString1.equals("")))
          {
            int x = JOptionPane.showConfirmDialog(null,"Do you want to Save file ?");
            if (x == 0)
            {
              int returnVal = fc.showSaveDialog(frame);
              if (returnVal == JFileChooser.APPROVE_OPTION)
              {
                filen = fc.getSelectedFile();
                frame.setTitle(filen.getName());
                try
                {
                  FileOutputStream f1 = new FileOutputStream(filen);
                  String  dataString = dataArea.getText();
                  byte b[] = dataString.getBytes();
                  f1.write(b);
                  f1.close();
                  System.exit(0);
                }
                catch (Exception e){}
              }
            }
            else if(x == JOptionPane.NO_OPTION)
            {
              System.exit(0);
            }
            else
            {

            }
          }
          else
          {
            System.exit(0);
          }
        }
      }
      else if (ae.getSource()==filePrint)
      {
        
      }
      else if (ae.getSource()==filePageSetup)
      {
        
      }
      else if (ae.getSource()==editUndo)
      {
        
      }
      else  if (ae.getSource()==editCut)
      {
        dataArea.cut();
      }
      else if (ae.getSource()==editCopy)
      {
        dataArea.copy();
      }
      else if (ae.getSource()==editPaste)
      {
        dataArea.paste();
      }
      else if (ae.getSource() == editSelectAll)
      {
        dataArea.selectAll();
      }
      else if (ae.getSource() == editDelete)
      {
        dataArea.replaceSelection("");
      }
      else if (ae.getSource() == editFind)
      {

      }
      else if (ae.getSource() == formatFont)
      {
        jd.setSize(830,500);
        jd.setVisible(true);
      }
      else if(ae.getActionCommand().equals("X"))
      {
        subframe.dispose();
      }
      else if(ae.getActionCommand().equals("OK"))
      {
        Font f;
        intFontList = fontlist.getSelectedIndex();
        intFontStyle = fontstyles.getSelectedIndex();
        intFontSize = fontsize.getSelectedIndex();
        String f1 = (String)fontlist.getItemAt(intFontList);
        String f2 = (String)fontstyles.getItemAt(intFontStyle);
        String f3 = (String)fontsize.getItemAt(intFontSize);
        int    f4 = Integer.parseInt(f3);
        textRed = newFontColor.getRed(); textGreen = newFontColor.getGreen(); textBlue = newFontColor.getBlue();
        bgRed = newBgColor.getRed(); bgGreen = newBgColor.getGreen(); bgBlue = newBgColor.getBlue();
        String ab = f1+","+f2+","+f3+","+textRed+","+textGreen+","+textBlue+","+bgRed+","+bgGreen+","+bgBlue+","+intFontList+","+intFontStyle+","+intFontSize;//+","+fgColor+","+bgColor;
        if (f2.equals("Regular"))
        {
          f = new Font(f1,Font.PLAIN,f4);
          dataArea.setFont(f);
        }
        else if (f2.equals("Italic"))
        {
          f = new Font(f1,Font.ITALIC,f4);
          dataArea.setFont(f);
        }
        else if (f2.equals("Bold"))
        {
          f = new Font(f1,Font.BOLD,f4);
          dataArea.setFont(f);
        }
        else
        {
          f = new Font(f1,Font.BOLD|Font.ITALIC,f4);
          dataArea.setFont(f);
        }
        dataArea.setBackground(newBgColor);
        dataArea.setForeground(newFontColor);
        // writing information of fonts in file
        try
        {
          FileOutputStream fontdata = new FileOutputStream("fontdata.txt");
          byte b2[] = ab.getBytes();
          fontdata.write(b2);
          fontdata.close();
        }catch(Exception e){}
        //
        subframe.dispose();
      }
      else if (ae.getActionCommand().equals("Cancel"))
      {
        subframe.dispose();
      }
    }
  public void itemStateChanged (ItemEvent ie)
  {
    if (formatWordWrap.isSelected())
      dataArea.setWrapStyleWord(true);
    else
      dataArea.setWrapStyleWord(false);
  }
    private void createGUI()
    {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        //file.getAccessibleContext().getAccessibleDescription("Opens files menu");
        menuBar.add(file);
        fileNew = new JMenuItem("New");
        fileNew.setAccelerator(KeyStroke.getKeyStroke('N',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileNew.addActionListener(this);
        file.add(fileNew);
        fileOpen = new JMenuItem("Open");
        fileOpen.setAccelerator(KeyStroke.getKeyStroke('O',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileOpen.addActionListener(this);
        file.add(fileOpen);
        fileSave = new JMenuItem("Save");
        fileSave.setAccelerator(KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileSave.addActionListener(this);
        file.add(fileSave);
        fileSaveAs =new JMenuItem("Save As");
        fileSaveAs.addActionListener(this);
        file.add(fileSaveAs);
        file.addSeparator();
        filePageSetup = new JMenuItem("Page Setup");
        filePageSetup.addActionListener(this);
        file.add(filePageSetup);
        filePrint = new JMenuItem("Print");
        filePrint.setAccelerator(KeyStroke.getKeyStroke('P',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        filePrint.addActionListener(this);
        file.add(filePrint);
        file.addSeparator();
        fileExit = new JMenuItem("Exit");
        fileExit.setAccelerator(KeyStroke.getKeyStroke('E',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileExit.addActionListener(this);
        file.add(fileExit);
        edit = new JMenu("Edit");
        menuBar.add(edit);
        edit.setMnemonic(KeyEvent.VK_E);
        editUndo = new JMenuItem("Undo");
        editUndo.setAccelerator(KeyStroke.getKeyStroke('Z',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editUndo.addActionListener(this);
        edit.add(editUndo);
        edit.addSeparator();
        editCut = new JMenuItem("Cut");
        editCut.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editCut.addActionListener(this);
        edit.add(editCut);
        editCopy = new JMenuItem("Copy");
        editCopy.setAccelerator(KeyStroke.getKeyStroke('C',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editCopy.addActionListener(this);
        edit.add(editCopy);
        editPaste = new JMenuItem("Paste");
        editPaste.setAccelerator(KeyStroke.getKeyStroke('V',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editPaste.addActionListener(this);
        edit.add(editPaste);
        editDelete = new JMenuItem("Delete");
        editDelete.addActionListener(this);
        edit.add(editDelete);
        edit.addSeparator();
        editFind = new JMenuItem("Find");
        editFind.setAccelerator(KeyStroke.getKeyStroke('F',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editFind.addActionListener(this);
        edit.add(editFind);
        editFindNext = new JMenuItem("Find Next");
        editFindNext.addActionListener(this);
        edit.add(editFindNext);
        editReplace = new JMenuItem("Replace");
        editReplace.setAccelerator(KeyStroke.getKeyStroke('H',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editReplace.addActionListener(this);
        edit.add(editReplace);
        editGoTO = new JMenuItem("Go to");
        editGoTO.setAccelerator(KeyStroke.getKeyStroke('G',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editGoTO.addActionListener(this);
        edit.add(editGoTO);
        edit.addSeparator();
        editSelectAll = new JMenuItem("Select All");
        editSelectAll.setAccelerator(KeyStroke.getKeyStroke('A',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editSelectAll.addActionListener(this);
        edit.add(editSelectAll);

        format = new JMenu ( "Format");
        format.setMnemonic(KeyEvent.VK_O);
        menuBar.add(format);
        cbMenuItem = new JCheckBoxMenuItem("Word Wrap");
        format.add(cbMenuItem);
        formatFont = new JMenuItem("Font");
        formatFont.setAccelerator(KeyStroke.getKeyStroke('T',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        formatFont.addActionListener(this);
        format.add(formatFont);

        dataArea.setEditable(true);
        dataArea.setDragEnabled(true);

        scrollPane = new JScrollPane(dataArea);
        frame = new JFrame("Notepad - Document1");

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.setSize(850,550);
        frame.setLocation(150,50);
        frame.setVisible(true);
        frame.addWindowListener( new AreYouSure() );
    }
    /// class for save dialog on Exit button press
    private class AreYouSure extends WindowAdapter
    {
      public void windowClosing( WindowEvent e )
      {
        if(!(frame.getTitle().equals("Notepad - Document1")))
            {
                try
                {
                    FileInputStream fin = new FileInputStream(filen);
                    size=fin.available();
                    byte b1[]=new byte[size];
                    fin.read(b1);
                    String datas=new String(b1);
                    String  dataString1 = dataArea.getText();
                    if (datas.equals(dataString1))
                    {
                        System.exit(0);
                    }
                    else
                    {
                        int x = JOptionPane.showConfirmDialog(null,"Do you want to save file ?");
                        if (x == 0)
                        {
                            // saving a file
                            try
                            {
                                FileOutputStream f1 = new FileOutputStream(filen);
                                String  dataString = dataArea.getText();
                                byte b[] = dataString.getBytes();
                                f1.write(b);
                                f1.close();
                            }
                            catch (Exception ae){}
                            System.exit(0);
                        }
                        else if (x == JOptionPane.NO_OPTION)
                        {
                            System.exit(0);
                        }
                        else{}
                    }
                }
                catch (Exception aaa){}
            }
            else
            {
                String  dataString1 = dataArea.getText();
                try
                {
                    if (!(dataString1.equals("")))
                    {
                        int x = JOptionPane.showConfirmDialog(null,"Do you want to save file ?");
                        if (x == 0)
                        {
                            int returnVal = fc.showSaveDialog(frame);
                            if (returnVal == JFileChooser.APPROVE_OPTION)
                            {
                                File file = fc.getSelectedFile();
                                frame.setTitle(file.getName());
                                try
                                {
                                    FileOutputStream f1 = new FileOutputStream(file);
                                    String  dataString = dataArea.getText();
                                    byte b[] = dataString.getBytes();
                                    f1.write(b);
                                    f1.close();
                                }
                                catch (Exception aae){}
                                System.exit(0);
                            }
                        }
                        else if (x == JOptionPane.NO_OPTION)
                        {
                            System.exit(0);
                        }
                        else{}
                    }
                    else
                    {
                        System.exit(0);
                    }
                }
                catch(Exception aaa){}
            }
          ///

      }
    }
      /// definition ends here
      public static void main (String RS[])
      {   dataArea = new JTextArea();
          fileFont=new File("fontdata.txt");
          Font f1;
          String fdatas="",sf[];
          sf = new String[12];
          int newSize,newtextRed=0,newtextGreen=0,newtextBlue=0,newbgRed=0,newbgGreen=0,newbgBlue=0;
          try
          {
            // Reading saved fonts from file
            try
            {
              FileInputStream fin = new FileInputStream("fontdata.txt");
              size=fin.available();
              byte b[]=new byte[size];
              fin.read(b);
              fdatas=new String(b);
              fin.close();
            }
            catch(Exception e){}
            // break data to set font
            StringTokenizer st = new StringTokenizer(fdatas,",");
            for (int i=0;i<12;i++)
            {
              sf[i] = st.nextToken();
            }
            newSize = Integer.parseInt(sf[2]);
            newtextRed = Integer.parseInt(sf[3]);
            newtextGreen = Integer.parseInt(sf[4]);
            newtextBlue = Integer.parseInt(sf[5]);
            newbgRed = Integer.parseInt(sf[6]);
            newbgGreen = Integer.parseInt(sf[7]);
            newbgBlue = Integer.parseInt(sf[8]);
            intFontList = Integer.parseInt(sf[9]);
            intFontStyle = Integer.parseInt(sf[10]);
            intFontSize = Integer.parseInt(sf[11]);

            newBgColor=c1 = new Color(newbgRed,newbgGreen,newbgBlue);
            newFontColor=c2 = new Color(newtextRed,newtextGreen,newtextBlue);
            if(fileFont.exists() && fileFont.isFile())
            {
              jc1=new JColorChooser(c2);
              jc2=new JColorChooser(c1);
            }
            //jc2.setColor(c2);
            dataArea.setBackground(c1);
            dataArea.setForeground(c2);
            if (sf[1] == "Regular")
            {
              f1 = new Font(sf[0],Font.PLAIN,newSize);
              dataArea.setFont(f1);
            }
            else if (sf[1] == "Italic")
            {
              f1 = new Font(sf[0],Font.ITALIC,newSize);
              dataArea.setFont(f1);
            }
            else if (sf[1] == "Bold")
            {
              f1 = new Font(sf[0],Font.BOLD,newSize);
              dataArea.setFont(f1);
            }
            else
            {
              f1 = new Font(sf[0],Font.BOLD|Font.ITALIC,newSize);
              dataArea.setFont(f1);
            }

          }
          catch(Exception ae){}

    notepad n1=new notepad();
    n1.createGUI();

    }
    notepad()
    {
        super("notepad thread");
        start();
    }
 }
