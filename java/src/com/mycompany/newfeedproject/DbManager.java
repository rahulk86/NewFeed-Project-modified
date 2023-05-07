
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
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
       cvoDvUserDataBaseName = "tabnewfeedNew";
       cvoDburl              = "jdbc:mysql://localhost:3306";
       cvoDvUserName         = "root";
       cvoDbPasssword        = "Rahul@123";
       Path currentDirectoryPath = FileSystems.getDefault().getPath("");
       cvsFolderPath = currentDirectoryPath.toAbsolutePath().toString()+"\\sql\\";
        try {
            setConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DbManager(){
        cvoRplcValMap  = new HashMap<>();
    }
    public static void setConnection() throws SQLException, FileNotFoundException{
      cvocon = DriverManager.getConnection(cvoDburl, cvoDvUserName, cvoDbPasssword); 
       ResultSet resultSet = cvocon.getMetaData().getCatalogs();
        boolean isPrsnt = false;
        while (resultSet.next()) {
            String databaseName = resultSet.getString(1);
            if(databaseName.equals(cvoDvUserDataBaseName.toLowerCase())) { 
                isPrsnt = true;
            }
        }
        if (!isPrsnt) {
            DbManager cvoDbManager = new DbManager();
            PreparedStatement pstm = cvocon.prepareStatement("CREATE DATABASE "+cvoDvUserDataBaseName);
            pstm.executeUpdate();
            pstm = cvocon.prepareStatement("USE "+cvoDvUserDataBaseName);
            pstm.executeUpdate();
            Path currentDirectoryPath = FileSystems.getDefault().getPath("");
            String path = currentDirectoryPath.toAbsolutePath().toString() + "\\setupdatabase";
            File lvoFile = new File(path);
            for (File file : lvoFile.listFiles()) {
                pstm = cvocon.prepareStatement(cvoDbManager.readFile(file));
                pstm.executeUpdate();
            }
        }
        else{
            PreparedStatement pstm = cvocon.prepareStatement("USE "+cvoDvUserDataBaseName);
            pstm.executeUpdate();
        }
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
        File lvoFile = new File(cvsFolderPath+pvoFile+".sql");
        if(!lvoFile.exists()){
            throw new RuntimeException("File are not Exits");
        }
        PreparedStatement pstm = cvocon.prepareStatement(readFile(lvoFile));
        return pstm.executeQuery();
    }
    
    public int update(String pvoFile) throws FileNotFoundException, SQLException{
         File lvoFile = new File(cvsFolderPath+pvoFile+".sql");
        if(!lvoFile.exists()){
            throw new RuntimeException("File are not Exits");
        }
        PreparedStatement pstm = cvocon.prepareStatement(readFile(lvoFile));
        return pstm.executeUpdate();
    }
    private String readFile(File pvoFile) throws FileNotFoundException {
        FileReader lvoFileReader = new FileReader(pvoFile);
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
    public static DBdateTime diffLocalDateTime(LocalDateTime pvdLocalDateTime1 ,LocalDateTime pvdLocalDateTime2){
        DBdateTime svoDBdateTime= new DBdateTime();
        Period period = Period.between(pvdLocalDateTime1.toLocalDate(), pvdLocalDateTime2.toLocalDate());
        svoDBdateTime.setDBYear(period.getYears());
        svoDBdateTime.setDBMonth(period.getMonths());
        svoDBdateTime.setDBDay(period.getDays());
         Duration duration = Duration.between(pvdLocalDateTime1, pvdLocalDateTime2);
        long seconds = duration.getSeconds();
        svoDBdateTime.setDBHour((int)(seconds / DBdateTime.SECONDS_PER_HOUR));
        svoDBdateTime.setDBMinutes((int)((seconds % DBdateTime.SECONDS_PER_HOUR) / DBdateTime.SECONDS_PER_MINUTE));
        svoDBdateTime.setDBsecond((int)(seconds % DBdateTime.SECONDS_PER_MINUTE));
        return svoDBdateTime;
    }
    public static class DBdateTime{
        static final int MINUTES_PER_HOUR = 60;
        static final int SECONDS_PER_MINUTE = 60;
        static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
        private int DBYear;
        private int DBMonth;
        private int DBDay;
        private int DBHour;
        private int DBMinutes;
        private int DBsecond;

        public int getDBYear() {
            return DBYear;
        }

        public void setDBYear(int DBYear) {
            this.DBYear = DBYear;
        }

        public int getDBMonth() {
            return DBMonth;
        }

        public void setDBMonth(int DBMonth) {
            this.DBMonth = DBMonth;
        }

        public int getDBDay() {
            return DBDay;
        }

        public void setDBDay(int DBDay) {
            this.DBDay = DBDay;
        }

        public int getDBHour() {
            return DBHour;
        }

        public void setDBHour(int DBHour) {
            this.DBHour = DBHour;
        }

        public int getDBMinutes() {
            return DBMinutes;
        }

        public void setDBMinutes(int DBMinutes) {
            this.DBMinutes = DBMinutes;
        }

        public int getDBsecond() {
            return DBsecond;
        }

        public void setDBsecond(int DBsecond) {
            this.DBsecond = DBsecond;
        }
        
    }
}
