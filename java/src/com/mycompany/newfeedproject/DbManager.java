
package com.mycompany.newfeedproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author DELL
 */
public class DbManager {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d-MMM-yyyy HH:mm:ss.SSS");
    private static String     cvoDbClassForName ;
    private static String     cvoDburl ;
    private static String     cvoDvUserName ;
    private static String     cvoDvUserDataBaseName ;
    private static String     cvoDbPasssword ;
    public static String      cvsFolderPath;
    private static Connection cvocon ;
    private Map<String,String> cvoRplcValMap;
    static {
       cvoDbClassForName     = "com.mysql.jdbc.Driver"; 
       cvoDvUserDataBaseName = "tabnewfeed";
       cvoDburl              = "jdbc:mysql://localhost:3306/"+cvoDvUserDataBaseName;
       cvoDvUserName         = "root";
       cvoDbPasssword        = "Rahul@123";
       Path currentDirectoryPath = FileSystems.getDefault().getPath("");
       cvsFolderPath = currentDirectoryPath.toAbsolutePath().toString()+"\\sql\\";
        try {
            cvocon = DriverManager.getConnection(cvoDburl, cvoDvUserName, cvoDbPasssword);
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DbManager(){
        cvoRplcValMap  = new HashMap<>();
    }
    public void addContextParm(String pvsRplcVal,Object pvoVal){
        if(pvoVal instanceof BigDecimal){
         cvoRplcValMap.put(pvsRplcVal, ((BigDecimal) pvoVal).toPlainString());
        }
        else if(pvoVal instanceof Integer){
         cvoRplcValMap.put(pvsRplcVal, pvoVal+"");   
        }
        else if(pvoVal instanceof Date){
         cvoRplcValMap.put(pvsRplcVal, ((Date)pvoVal).toString());   
        }
        else if(pvoVal instanceof LocalDate){
         cvoRplcValMap.put(pvsRplcVal, ((LocalDate)pvoVal).toString());   
        }
        else if(pvoVal instanceof LocalDateTime){
         cvoRplcValMap.put(pvsRplcVal, ((LocalDateTime)pvoVal).toString());   
        }
        else{
         cvoRplcValMap.put(pvsRplcVal, pvoVal.toString());    
        }
                
    }
    public ResultSet select(String pvoFile) throws SQLException, FileNotFoundException{
        PreparedStatement pstm = cvocon.prepareStatement(readFile(pvoFile));
        return pstm.executeQuery();
    }
    
    public int update(String pvoFile) throws FileNotFoundException, SQLException{
        PreparedStatement pstm = cvocon.prepareStatement(readFile(pvoFile));
        return pstm.executeUpdate();
    }
    private String readFile(String pvoFile) throws FileNotFoundException {
        
         File lvoFile = new File(cvsFolderPath+pvoFile+".sql");
        if(!lvoFile.exists()){
            throw new RuntimeException("File are not Exits");
        }
        FileReader lvoFileReader = new FileReader(lvoFile);
        BufferedReader br = new BufferedReader(lvoFileReader);
        Stream<String> lvoStrinReader = br.lines();
        StringBuilder sql = new StringBuilder();
        sql.append("\n");
        lvoStrinReader.forEach((s) -> {
            int strt = -1;
            StringBuilder lvoRplcStr = new StringBuilder(s);
            
            for (int i = 0; i < lvoRplcStr.length(); i++) {
                if (lvoRplcStr.charAt(i) == '<') {
                    strt = i;
                } else if (lvoRplcStr.charAt(i) == '>') {
                    String svoRplcVal = cvoRplcValMap.get(lvoRplcStr.substring(strt, i + 1));
                    svoRplcVal = svoRplcVal.replaceAll("'", "''");
                    lvoRplcStr.replace(strt, i + 1, svoRplcVal);
                }
            }
            
            sql.append(lvoRplcStr);
            sql.append("\n");
        });
        return sql.toString();

    }
    
}
