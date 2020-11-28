/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android_chat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Bala
 */
public class Fun {
    static int resultant=1;
    static String str1,str2,str3;
    Connection con=javaconnect.connectDB();
    public void Perform(String message){
        switch(resultant){
            case 1:
                str1=message;
                resultant++;
                break;
            case 2:
                str2=message;
                resultant++;
                break;
            case 3:
                str3=message;
                resultant=1;
                String sql="INSERT INTO LIST.NAMELIST (NAME,PHNO,MAIL) VALUES('"+str1+"','"+str2+"','"+str3+"')";
                
        try {
            Statement state;
            state = con.createStatement();
            state.execute(sql);
        } catch (Exception e) {
        }
        break;
        }
    
    }
}
