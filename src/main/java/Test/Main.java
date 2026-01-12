package Test;//哈夫曼编码，java，请使用utf-8

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {

    JFrame window1 = new JFrame("哈夫曼编码编译文本");
    window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window1.setBounds(300, 100, 800, 600);//初始窗口
    window1.setLayout(new BorderLayout());

    JTextArea text = new JTextArea();
    JScrollPane textarea = new JScrollPane(text);

    JButton button1 = new JButton("加密文档");
    button1.setPreferredSize(new Dimension(370, 80));

    class button1Action implements ActionListener {

      public void actionPerformed(ActionEvent e) {
        text.setText("请选择要加密的文件\n");
        String path = "";//文件路径
        JFileChooser fileDialog = new JFileChooser();

        int state = fileDialog.showOpenDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {
          path = String.valueOf(fileDialog.getSelectedFile());
          text.append(path + "\n请选择输出的文件夹路径\n");

          String path2 = "";//文件夹路径
          JFileChooser folder = new JFileChooser();
          folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int state2 = folder.showOpenDialog(null);
          if (state2 == JFileChooser.APPROVE_OPTION) {
            path2 = String.valueOf(folder.getSelectedFile());
            text.append(path2);

            makecode(path, path2);
            text.append("\n生成密码完毕,密码本命名为code，密文命名为code-text\n");
          }
        }
      }
    }
    button1.addActionListener(new button1Action());
////////////////////////////////////////////////////////////////////////////////////
    JButton button2 = new JButton("解密文档");
    button2.setPreferredSize(new Dimension(370, 80));

    class button2Action implements ActionListener {

      public void actionPerformed(ActionEvent e) {
        text.setText("请选择要解密的文件\n");
        String path = "";//文档路径
        JFileChooser fileDialog = new JFileChooser();
        int state = fileDialog.showOpenDialog(null);
        if (state == JFileChooser.APPROVE_OPTION) {
          path = String.valueOf(fileDialog.getSelectedFile());
          text.append(path);
          text.append("\n请选择对应密码\n");
          String path2 = "";//密码文件路径
          JFileChooser fileDialog2 = new JFileChooser();
          int state2 = fileDialog2.showOpenDialog(null);
          if (state2 == JFileChooser.APPROVE_OPTION) {
            path2 = String.valueOf(fileDialog2.getSelectedFile());
            text.append(path2 + "\n请选择输出的文件夹路径\n");

            String path3 = "";//文件夹路径
            JFileChooser folder = new JFileChooser();
            folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int state3 = folder.showOpenDialog(null);
            if (state3 == JFileChooser.APPROVE_OPTION) {
              path3 = String.valueOf(folder.getSelectedFile());
              text.append(path3);

              gettext(path, path2, path3);

              text.append("\n解密完毕，生成译文命名为decode-text\n");
            }

          }
        }
      }
    }
    button2.addActionListener(new button2Action());
////////////////////////////////////////////////////////////////////////////////////
    window1.add(textarea, BorderLayout.CENTER);
    window1.add(button1, BorderLayout.NORTH);
    window1.add(button2, BorderLayout.SOUTH);

    window1.setVisible(true);

  }

  static void createlist(LinkedList<elm> list, String str) {
    for (int i = 0; i < str.length(); i++) {
      if (checklist(list, str.charAt(i)) == true && str.charAt(i) != '\n') {
        elm tmp = create(str, str.charAt(i));
        list.add(tmp);
        //System.out.println("add 1");
      }
//            else{
//                System.out.println("#");
//            }
    }
  }//将一个字符串转化为结构体存入列表中

  static void viewlist(LinkedList<elm> list) {
    Iterator<elm> iter = list.iterator();
    while (iter.hasNext()) {
      elm tmp = iter.next();
      System.out.println(tmp.name + ":" + tmp.value);
    }
  }//遍历列表

  static elm create(String str, char x) {
    elm tmp = new elm(x, count(str, x));
    return tmp;
  }//创建结构体

  static boolean checklist(LinkedList<elm> list, char x) {
    Iterator<elm> iter = list.iterator();
    while (iter.hasNext()) {
      char tmp = iter.next().name;
      if (tmp == x) {
        //System.out.println(x+" existed!");
        return false;
      }
    }
    //System.out.println(x+" doesn't exist.");
    return true;
  }//检查链表list中是否存在含有x的结构体

  static int count(String str, char x) {
    int cnt = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == x) {
        cnt++;
      }
    }
    return cnt;
  }//计数，字符串str中的x个数

  static class elm {

    char name = '#';//元素本身字符
    int value = 0;//元素权值
    int self = -1;//自身为左子树还是右子树，-1为未确定，0为左，1为右
    String code = "";//元素应生成的密码

    elm(char x, int y) {
      this.name = x;
      this.value = y;
    }//构建一个元素

    elm left = null;//左孩子节点
    elm right = null;//有孩子节点
    elm parent = null;//双亲节点

    char getName() {
      return this.name;
    }

    String getCode() {
      return this.code;
    }

    void setleft(elm left) {
      left.self = 0;
      this.left = left;
    }//将一个结构体作为该元素左孩子

    void setright(elm right) {
      right.self = 1;
      this.right = right;
    }//将一个结构体作为该元素右孩子

    void setparent(elm parent) {
      this.parent = parent;
    }//将一个结构体作为该元素父母节点
  }//元素结构体

  static elm getmin(LinkedList<elm> list) {
    Iterator<elm> iter = list.iterator();
    elm min = iter.next();
    while (iter.hasNext()) {
      elm tmp = iter.next();
      if (tmp.value < min.value) {
        min = tmp;
      }
    }
    return min;
  }//获取列表中的权值最小节点

  static void maketree(LinkedList<elm> list) {
    while (list.size() > 1) {
      elm l1 = getmin(list);
      list.remove(l1);
      elm l2 = getmin(list);
      list.remove(l2);
      elm l3 = new elm('#', l1.value + l2.value);
      l3.setleft(l1);
      l1.setparent(l3);
      l3.setright(l2);
      l2.setparent(l3);
      list.add(l3);
      System.out.println("made a line");
      viewlist(list);
    }
    System.out.println("have made a tree");
  }//构建哈夫曼树

  static void viewtree(elm root) {
    if (root != null) {
      System.out.println(root.name + ":" + root.value + "***" + root.code);
      viewtree(root.left);
      viewtree(root.right);
    }
  }//先根序遍历哈夫曼树

  static void setcode(elm root) {
    if (root != null) {
      if (root.parent != null && root.self == 0) {
        root.code = root.parent.code + "0";
      } else if (root.parent != null && root.self == 1) {
        root.code = root.parent.code + "1";
      }
      setcode(root.left);
      setcode(root.right);
    }
  }//给哈夫曼树设置密码

  static void makelist(elm root, LinkedList<String> tmp) {
    if (root != null) {
      if (root.getName() != '#') {
        tmp.add(root.getName() + ":" + root.getCode());
      }
      makelist(root.left, tmp);
      makelist(root.right, tmp);
    }
  }//创建字典列表

  static String changetext(LinkedList<String> list, String str) {
    String[] code = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      code[i] = list.get(i);
    }
    String[] text = str.split("");
    for (int i = 0; i < text.length; i++) {
      for (int j = 0; j < code.length; j++) {
        if (code[j].charAt(0) != ':') {
          String[] tmp = code[j].split(":");
          if (text[i].equals(tmp[0])) {
            text[i] = tmp[1];
          }//如果输入文本不包含密码中的分隔符
        } else {
          String[] tmp = code[j].split(":");
          if (text[i].equals(":")) {
            text[i] = tmp[2];
          }
        }//如果输入文本包含密码中的分隔符
      }
    }
    String result = "";
    for (int i = 0; i < text.length; i++) {
      result = result + text[i];
    }
    return result;
  }//将str中的原文全部替换为密码

  static void makecode(String filepath, String path) {
    File f = new File(filepath);
    File f2 = new File(path + "\\code.txt");
    File f3 = new File(path + "\\code-text.txt");
    try {
      FileReader getOne = new FileReader(f);
      BufferedReader get = new BufferedReader(getOne);
      String str = "";
      String get1 = "";
      while ((get1 = get.readLine()) != null) {
        str = str + get1 + "\n";
      }//通过text文件获取原文================================================================

      LinkedList<elm> list = new LinkedList<elm>();
      createlist(list, str);
      viewlist(list);
      maketree(list);
      setcode(list.get(0));
      viewtree(list.get(0));//将原文生成哈夫曼树

      LinkedList<String> tmp = new LinkedList<String>();
      makelist(list.get(0), tmp);
      String code = "";
      for (int i = 0; i < tmp.size(); i++) {
        code = code + tmp.get(i) + " ";
      }
      System.out.print(code);//生成密码

      System.out.println();
      System.out.println(str);
      getOne.close();
      get.close();//获取text文档中的字符=====================================================

      FileWriter outOne = new FileWriter(f2);
      BufferedWriter out = new BufferedWriter(outOne);
      out.write(code);
      out.close();
      outOne.close();//将密码创建为一个文件
      //System.out.print("succeed!");

      FileWriter outOne1 = new FileWriter(f3);
      BufferedWriter out1 = new BufferedWriter(outOne1);
      String text2 = changetext(tmp, str);
      out1.write(text2);
      out1.close();
      outOne1.close();//将加密后的文本创建为一个文件

      list.clear();
      System.out.println("已加密，密码本为code.txt，加密文本为code-text.txt");

    } catch (IOException e) {
      System.out.println(e);
    }
  }//创建密码本文件

  static void gettext(String filepath, String filepath2, String path) {
    File f1 = new File(filepath2);//密码本
    File f2 = new File(filepath);//密文
    File f3 = new File(path + "\\decode-text.txt");//解密后
    try {
      FileReader getOne = new FileReader(f1);
      BufferedReader get = new BufferedReader(getOne);
      String code = "";
      String get1 = "";
      while ((get1 = get.readLine()) != null) {
        code = code + get1 + "\n";
      }
      getOne.close();
      get.close();
      //获取密码code
      System.out.println(code);

      FileReader getTwo = new FileReader(f2);
      BufferedReader get2 = new BufferedReader(getTwo);
      String str = "";
      String get0 = "";
      while ((get0 = get2.readLine()) != null) {
        str = str + get0 + "\n";
      }
      getOne.close();
      get.close();
      System.out.println(str);//获取原文str

      String[] codeList = code.split(" ");
      String tmpcode = "";
      String result = "";
      for (int i = 0; i < str.length(); i++) {
        tmpcode = tmpcode + str.charAt(i);
        for (int j = 0; j < codeList.length; j++) {
          String[] tmplist = codeList[j].split(":");

          String[] tmplist2 = codeList[j].split("");

          if (tmpcode.equals(tmplist[tmplist.length - 1])) {
//                        for(int k=0;k<tmplist.length;k++){
//                            System.out.println(tmplist[k]);
//                        }
//                        System.out.println("###################");
//                        for(int k=0;k<tmplist2.length;k++){
//                            System.out.println(tmplist2[k]);
//                        }
//                        System.out.println("@@@@@@@@@@@@@@@@@@@");//校验区
            if (!tmplist2[0].equals(":")) {
              result = result + tmplist2[0];
            }
            if (tmplist2[0].equals(":")) {
              if (tmplist2[1].equals(":")) {
                result = result + ":";
              } else {
                result = result + " ";
              }
            }//遇到空格的处理
            tmpcode = "";
          }
        }
      }
      //生成原文字符串result
      System.out.println(result);
      FileWriter outOne1 = new FileWriter(f3);
      BufferedWriter out1 = new BufferedWriter(outOne1);
      out1.write(result);
      out1.close();
      outOne1.close();//将加密后的文本创建为一个文件
      System.out.println("已解密，生成文件为decode-text.txt");
    } catch (IOException e) {
      System.out.println(e);
    }
  }//生成译文文件
}