package Supply_chain_management;
import java.sql.*;
import java.util.*;
import java.time.*;

public class User_methods extends Login_signup
{
    public static String updatefield, newpass;

    public static int arrz[] = {};
    
    public static void user_main_page()
    {
        try
        {
            String option;

            System.out.println("                         [User Profile]");

            System.out.println("\n[Manage My Info | Manage Orders | Communicate | LogOut]");
            
            System.out.print("\nEnter your Option: "); 
            option = sc.nextLine();

            switch (option.toLowerCase())
            {
                case "manage my info":
                {
                    newpass = "";

                    manage_my_info(username,newpass);

                    break;
                }

               case "manage orders":
                {
                    manage_orders();

                    break;
                }

                case "communicate":
                {
                    communicate();

                    break; 
                } 

                case "logout":
                {
                    intro();

                    break;
                }

                default:
                {
                    System.out.println("Invalid Input!");

                    user_main_page();
                }
            }            
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void manage_my_info(String username, String newpass)
    {   
        try
        {
            String query;

            ResultSet rs;

            Login_signup.db_connect();
            
            query = "SELECT * FROM user_info WHERE Username = '"+username+"'";

            rs = st.executeQuery(query);
            
            rs.next();
            
            System.out.println("\nUserID: "+rs.getString(1));
            System.out.println("Username: "+rs.getString(2));
            System.out.println("Name: "+rs.getString(3));
            System.out.println("Mobile: "+rs.getString(5));
            System.out.println("Email: "+rs.getString(6));

            update_info(username,newpass);
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("An Error Occured!");
        }
    }

    public static void update_info(String username, String newpass)
    {
        try
        {
            String option, updatefield;

            System.out.println("\n[Update info | Exit to main page]");

            System.out.print("\nEnter your option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("update info") || option.toLowerCase().equals("update"))
            {
                System.out.println("\n[Name | Mobile | Email | Password]");

                System.out.print("\nEnter the info to be updated: ");
                updatefield = sc.nextLine();
            
                updatefield = updatefield.toLowerCase();

                while (updatefield.equals("username") || updatefield.toLowerCase().equals("userid"))
                {
                    System.out.println("Username or UserID cannot be changed!");
                    updatefield = sc.nextLine();
                }

                String arr[] = {"name","mobile","email","password","username","userid"};

                while (!Arrays.asList(arr).contains(updatefield))
                { 
                    System.out.println("\nField not found!");
                    System.out.print("\nEnter the info to be updated: ");
                    updatefield = sc.nextLine();
                }

                change(updatefield,newpass);
            }

            else if (option.toLowerCase().equals("exit to main page") || option.toLowerCase().equals("exit"))
            {
                user_main_page();
            }

            else
            {
                System.out.println("\nInvalid Option!");
                
                update_info(username, newpass);
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void change(String updatefield, String newpass)
    {
        try
        {
            String query, query1, update, dbpass, oldinfo = "", change_db = ""; 
        
            int i;

            ResultSet rs;
            
            System.out.print("\nEnter the new "+updatefield+": ");
            update = sc.nextLine();
            
            query = "SELECT Password,Name,Mobile,Email FROM user_info WHERE Username = '"+username+"'";

            Login_signup.db_connect();

            rs = st.executeQuery(query);

            rs.next();

            dbpass = rs.getString(1);

            if (newpass.equals(""))
            {
                System.out.print("\nEnter user password to change "+updatefield+": ");
                newpass = sc.nextLine();
            }

            for (i=0;i<=1;++i)
            {
                if (newpass.equals(dbpass))
                {
                    if (updatefield.equals("name"))
                    {
                        change_db = "Name";
                        oldinfo = rs.getString(2);
                    }

                    else if (updatefield.equals("mobile"))
                    {
                        change_db = "Mobile";
                        oldinfo = rs.getString(3);
                    }

                    else if (updatefield.equals("email") || updatefield.equals("e-mail"))
                    {
                        change_db = "Email";
                        oldinfo = rs.getString(4);
                    }

                    else if (updatefield.equals("password"))
                    {
                        change_db = "Password";
                        oldinfo = newpass;
                    }

                    if (update.equals(oldinfo))
                    {
                        System.out.println("\n Updating "+change_db+" cannot be the same as existing "+updatefield+"! ");
                        
                        manage_my_info(username,dbpass);
                    }

                    else
                    {
                        query1 = "UPDATE user_info SET "+change_db+" = '"+update+"' WHERE Username = '"+username+"'";
                        
                        db_update(query1); 
                        
                        System.out.println("\n"+updatefield+" Updated Successfully!");
                        
                        manage_my_info(username,dbpass);
                    }
                    
                    break;
                }

                else if (i==1)
                {
                    System.out.println("Max limit reached (Wrong password!)\nYou are logged out!");
                    intro();
                }

                else                    
                {
                    System.out.println("\nIncorrect Password! Try Again!");

                    System.out.print("\nEnter your current password to change "+updatefield+": ");
                    newpass = sc.nextLine();
                }
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch (Exception e)
        {
            System.out.println("An Error Occured !");
        }
    }

    public static void manage_orders()
    {
        try
        {
            String option;

            System.out.println("\n[Place Orders | View Orders | Exit]");

            System.out.print("\nEnter your Option: ");
            option = sc.nextLine();
            
            switch(option.toLowerCase())
            {
                case "place orders":
                {
                    place_orders();

                    break;
                }

                case "view orders":
                {
                    view_orders();

                    break;
                }

                case "exit":
                {
                    user_main_page();

                    break;
                }

                default:
                {
                    System.out.println("\nInvalid Input!");

                    manage_orders();
                }
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void place_orders()
    {
        String option;

        try
        {
            System.out.println("\n[Enter Order Details | Exit to Manage Orders]");

            System.out.print("\nEnter your Option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("enter order details") || option.toLowerCase().equals("enter details"))
            {
                enter_order_details();
            }

            else if (option.toLowerCase().equals("exit") || option.toLowerCase().equals("exit to manage orders"))
            {
                manage_orders();
            }

            else
            {
                System.out.println("Invalid Input!");
                place_orders();
            }
        }
        
        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void enter_order_details()
    {
        try
        {
            String query, query1, query2, query3, orderitem, productid, productname;

            int quantity, cost, totalcost, orderid;

            ResultSet rs, rs1, rs2;

            Login_signup.db_connect();

            query = "SELECT `Product Name` FROM product_info";

            rs = st.executeQuery(query);

            System.out.println("\nAvailable Products:");

            while (rs.next())
            {
                System.out.print("\n"+rs.getString("Product Name"));
            }

            System.out.print("\n\nEnter your Component Name: ");
            orderitem = sc.nextLine();

            query1 = "SELECT * FROM product_info where `Product Name` = '"+orderitem+"'";

            rs1 = st.executeQuery(query1);

            if (rs1.next())
            {
                System.out.println("\nProduct information \n");
            
                System.out.println("Product ID: "+rs1.getString(1));
                System.out.println("Product Name: "+rs1.getString(2));
                System.out.println("Cost of Each Product: Rs."+rs1.getString(4)+"/-");
                System.out.println("Precision Rate: "+rs1.getString(7)+" %");
                
                productid = rs1.getString(1);

                productname = rs1.getString(2);
                
                cost = rs1.getInt(4);

                System.out.print("\nEnter Quantity: ");
                quantity = sc.nextInt();
                            
                totalcost = cost*quantity;

                System.out.println("\nTotal Bill: "+totalcost);

                LocalDateTime ordertime = LocalDateTime.now();

                query2 =  "INSERT INTO customer_order_info (`Customer Username`,`Order Date`,`Product ID`,Quantity,`Cost of Order`,`Payment Status`) Values ('"+username+"','"+ordertime+"','"+productid+"','"+quantity+"','"+totalcost+"','Pending');";
                
                db_update(query2);

                query3 = "SELECT * FROM customer_order_info WHERE `Customer Username` = '"+username+"';";

                rs2 = st.executeQuery(query3);

                while(rs2.next())
                {
                    System.out.println("\nOrder ID: "+rs2.getInt(1));
                    System.out.println("Order Date: "+rs2.getString(3));
                    System.out.println("Product ID: "+rs2.getString(4));
                    System.out.println("Quantity: "+rs2.getInt(5));
                    System.out.println("Cost: "+rs2.getInt(6)+"\n");
                }

                System.out.print("\nEnter the Order ID: ");
                orderid = sc.nextInt();

                Admin_methods.add_manu_info_to_database(productid,username,productname,"Batch",ordertime,ordertime.plusDays(2),"Order Recieved","null",totalcost,quantity);

                Admin_methods.add_dist_info_to_database(orderid,username,productid,ordertime,ordertime.plusDays(2),cost);

                System.out.println("\nOrder Recieved Successfully!\nPayment Details will be shared during the time of product delivery!");
                
                manage_orders();
            }

            else
            {
                System.out.println("Product not available!");

                enter_order_details();
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void view_orders()
    {
        try
        {
            String query, option;

            int orderid;

            boolean exists;

            ResultSet rs;

            query = "SELECT * FROM customer_order_info WHERE `Customer Username` = '"+username+"';";

            exists = Login_signup.check(query);

            db_connect();

            rs = st.executeQuery(query);

            if(exists)
            {
                System.out.println("\nYour orders!\n");

                while(rs.next())
                {
                    orderid = rs.getInt(1);

                    System.out.print("Order ID: "+orderid);
                    System.out.print("\nOrder Date: "+rs.getString(3));
                    System.out.print("\nProduct ID: "+rs.getString(4));
                    System.out.print("\nQuantity: "+rs.getInt(5));
                    System.out.print("\nTotal Cost: "+rs.getInt(6));
                    System.out.print("\nPayment Status: "+rs.getString(7)+"\n\n");

                    arrz = Arrays.copyOf(arrz,arrz.length+1); 
                    arrz[arrz.length-1] = orderid;
                }

                System.out.println("\n[Cancel Orders | Exit]");

                System.out.print("\nEnter your option: ");
                option = sc.nextLine();
            
                if (option.toLowerCase().equals("cancel orders") || option.toLowerCase().equals("cancel"))
                {
                    cancel_orders();
                }

                else if (option.toLowerCase().equals("exit"))
                {
                    manage_orders();
                }
            }
            
            else
            {
                System.out.println("No Orders Active!\nPlease Place order before viewing!!");

                manage_orders();
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("An Error Occured!");
        }
    }

    public static void cancel_orders()
    {
        try
        {
            System.out.println("\nView Orders before cancelling them!");

            String query;

            int cancelorderid;

            boolean exists;
        
            System.out.print("\nEnter the Order ID to cancel the order: ");
            cancelorderid = sc.nextInt();

            exists = check_orders(cancelorderid);
            
            if (exists)
            {
                query = "DELETE FROM customer_order_info WHERE `Order ID` = '"+cancelorderid+"';";

                Login_signup.db_update(query);

                System.out.println("Order Cancelled! ");
            }

            else
            {
                System.out.println("\nNo Active orders with Order ID: "+cancelorderid+"\nNo Orders Cancelled!");
            }

            manage_orders();        
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("An Error Occured!");
        }
    }

    public static boolean check_orders(int cancelorderid)
    {
        try
        {
            for (int i : arrz)
            {
                if (i == cancelorderid)
                {
                    return true;
                }
            }

            return false;
        }
        
        catch(Exception e)
        {
            System.out.println("An Error Occured!");
        }

        return false;
    }

    public static void communicate()
    {
        String option;

        try
        {
            System.out.println("\nWelcome to Communication Section!\n\nYour Message will be recorded and get response ASAP");
            
            System.out.print("\n[Enter Statement | Exit]\n\nEnter your option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("enter statement") || option.toLowerCase().equals("enter"))
            {
                enter_statement();
            }

            else if (option.toLowerCase().equals("exit"))
            {
                user_main_page();
            }

            else
            {
                System.out.println("Invalid Input!");

                communicate();
            }
        }
        
        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("An Error Occured!");
        }
    }

    public static void enter_statement()
    {
        try
        {
            String messagetype, message, query;

            LocalDateTime timeofmessage;
        
            System.out.print("\n[Compliment | Feedback | Complaint | Help]\n\nEnter your reason: ");
            messagetype = sc.nextLine();

            System.out.println("\nEnter the Statement (Do not use single quotes( ' (or) '' ) & Do not exceed 255 letters): \n");
            message = sc.nextLine();

            timeofmessage = LocalDateTime.now();

            query = "INSERT INTO communication_info (Username,`Message Type`,Message,`Time of Message`)Values('"+username+"','"+messagetype+"','"+message+"','"+timeofmessage+"')";

            Login_signup.db_update(query);

            System.out.println("\n"+messagetype+" has been recorded! & you will recieve our response soon!");

            user_main_page();
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }
}
