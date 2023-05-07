import com.mycompany.newfeedproject.DbManager;
import com.mycompany.newfeedproject.NewFeedUserControler;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class NewFeedProject {

    
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DbManager cvoDbManager = new DbManager();
        boolean exit = true;
        while(exit){
            NewFeedUserControler svoNewFeedUserControler = new NewFeedUserControler(cvoDbManager);
            Scanner s = new Scanner(System.in);
            System.out.println();
            System.out.println();
            System.out.println("1.[SignUp]");
            System.out.println();
            System.out.println("2.[Login]");
            System.out.println();
            System.out.println("3.[Exit]");
            System.out.println();
            System.out.println("Enter selected number");
            int num = s.nextInt();
            switch (num) {
                case 1: svoNewFeedUserControler.signUp();break;
                case 2:svoNewFeedUserControler.login();break;
                case 3:exit = false;break;
                default:System.out.println("Invalid number");
            }
        }
     
    }
}
