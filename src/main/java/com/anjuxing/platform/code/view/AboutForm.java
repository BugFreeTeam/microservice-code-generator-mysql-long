package com.anjuxing.platform.code.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutForm extends JDialog {

	private static final long serialVersionUID = 1L;

	public AboutForm() {
        initCommpant();
    }

    private void initCommpant() {
        this.setTitle("关于代码工厂");
        Toolkit toolkit = Toolkit.getDefaultToolkit(); 
        Dimension scmSize = toolkit.getScreenSize(); 
        this.setLocation(scmSize.width/2 - 230, scmSize.height/2 - 130); 
        this.setSize(460, 260);
        this.setBackground(Color.white);
        this.setAlwaysOnTop(true);
        
        this.setLayout(null);
        
        String html = "<html>本工具使用Java Swing开发，皮肤效果使用的开源的BeautyEye工具包，<br>"
                + "本工具旨在简化程序员开发过程中的繁琐工作，且降低错误率，提高开发效率。<br></html>";
        
        JLabel label = new JLabel(html);
        label.setBounds(20, 20, 420, 160);
        this.add(label);
        
    }
    
    public void showForm(){
        this.setVisible(true);
    }

}
