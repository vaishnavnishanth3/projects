package Supply_chain_management;
import java.sql.*;
import java.util.*;
import java.time.*;

public class Login_signup
{
    public static Scanner sc = new Scanner(System.in);

    public static String username;

    public static Connection con;

    public static Statement st;
    
    public static void db_connect()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supply_chain_management", "root", "12345");
            st = con.createStatement();
        }

        catch (Exception e)
        {
            System.out.println("\nAn Error Occurred!");
        }
    }

    public static void db_update(String query)
    {
        try
        {
            db_connect();

            st.executeUpdate(query);
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occurred!");
        }
    }

    public static boolean check(String query)
    {
        try
        {
            ResultSet rs;
            
            db_connect();

            rs = st.executeQuery(query);

            if(rs.next())
            {
                return true;
            }

            else
            {
                return false;
            }
        }

        catch(Exception e)
        {
            System.out.println("An Error Occured!");
        }

        return false;
    }

    public static void intro()
    {
        try
        {
            String option;

            System.out.print("\n                      SUPPLY CHAIN MANAGEMENT\n                      [Signup | Login | Quit]\n\nEnter your Option: ");

            option = sc.nextLine();

            while (option.toLowerCase().equals(""))
            {
                option = sc.nextLine();
            }

            switch (option.toLowerCase())
            {
                case "signup":
                {
                    signup();
                    break;
                }

                case "login":
                {
                    login();
                    break;
                }

                case "quit":
                {
                    break;
                }

                default:
                {
                    System.out.println("\nInvalid Input!");
                    intro();
                }
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occurred!");
        }
    }

    public static void signup() throws ClassNotFoundException, SQLException
    {
        try
        {
            String user_name, user_username, user_password, user_email, query, query1;

            long user_mobile;            
            
            boolean exists; 
            
            System.out.print("\nEnter your Name: ");
            user_name=sc.nextLine();

            System.out.print("Enter your Username: ");
            user_username = sc.nextLine();

            query = "SELECT * FROM user_info WHERE Username = '"+user_username+"'";

            exists = check(query);

            if (exists)
            {
                System.out.println("\nUsername Already exist! Try a new username!");
                System.out.print("\nEnter your Username: ");
                user_username = sc.nextLine();
            }

            while (user_username.startsWith("ADMIN"))
            {
                System.out.println("\nUsername Cannot start with 'ADMIN");
                user_username = sc.nextLine();
            }

            System.out.print("Enter your Password: ");
            user_password = sc.nextLine();

            System.out.print("Enter your Email: ");
            user_email = sc.nextLine();

            while (!user_email.endsWith(".com"))
            {
                System.out.print("\nInvalid Email!\nEnter your Email: ");
                user_email = sc.nextLine();
            }

            System.out.print("Enter your Mobile: ");
            user_mobile = sc.nextLong();

            LocalDateTime registeredtime = LocalDateTime.now();

            query1 = "INSERT INTO user_info (Name,Username,Password,Email,Mobile,`Registered Date & Time`) VALUES('"+user_name+"','"+user_username+"','"+user_password+"','"+user_email+"','"+user_mobile+"','"+registeredtime+"')";
                
            db_update(query1);

            System.out.print("\nData registered Successfully!!\n");
                
            intro();
        }

        catch (InputMismatchException i)
        {
            System.out.println("\nInvalid Data!\nUser data not Registered!\n");
            intro();
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void login()
    {
        try
        {
            String login_password, user_dbpassword, user_dbname, query;

            int i;

            ResultSet rs;

            System.out.println("\n                             LOGIN PAGE\n\nEnter your Details:");

            System.out.print("Username / Employee ID: ");
            username = sc.nextLine();
            
            System.out.print("password: ");
            login_password = sc.nextLine();

            if (username.startsWith("ADMIN"))
            {
                query = "SELECT * FROM admin_info WHERE `Admin ID` = '"+username+"'";
            }

            else
            {
                query = "SELECT * FROM user_info WHERE `Username` = '"+username+"'";
            }

            db_connect();

            rs = st.executeQuery(query);

            if (rs.next())
            {
                user_dbpassword = rs.getString(4);
                user_dbname = rs.getString(3);

                for (i=0;i<=1;++i)
                {
                    if (login_password.equals(user_dbpassword))
                    {
                        System.out.println("\nLogin Successfull!!\n                         Welcome "+user_dbname);
            
                        if (username.startsWith("ADMIN"))
                        {
                            Admin_methods.admin_main_page();
                            break;
                        }
                    
                        else
                        {
                            User_methods.user_main_page();
                            break;
                        }                       
                    }

                    else if (i==1)
                    {
                        System.out.println("\nMax limit reached, Try after sometimes.");
                        intro();
                    }

                    else
                    {  
                        System.out.println("\nIncorrect Password!");
                        System.out.print("password: ");
                        login_password = sc.nextLine();                    
                    }
                }
            }

            else
            {
                System.out.println("\nUser Not found, make sure to register/signup before logging in");
                intro();
            }
        }

        catch (InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch (Exception e)
        {
            System.out.println("\nAn Error Occured!");
            intro();
        }
    }
}
