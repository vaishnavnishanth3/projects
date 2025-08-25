package Supply_chain_management;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class Admin_methods extends Login_signup
{
    public static void admin_main_page()
    {
        try
        {
            String option;

            System.out.println("                         [Admin Profile]");

            System.out.println("\n[Manage My Info | Manage User info | Raw Materials Info | Supply info | Manufacturing Info | Distribution Info | LogOut]");
            
            System.out.print("\nEnter Your Option: "); 
            option = sc.nextLine();
            
            switch (option.toLowerCase())
            {
                case "manage my info":
                {
                    manage_admin_login_info();
                    break;
                }

                case "manage user info":
                {
                    manage_user_info();
                    break;
                }
                

                case "raw materials info":
                {
                    raw_materials_info();
                    break;
                }
                
                case "supply info":
                {
                    supply_info();
                    break;
                }

                case "manufacturing info":
                {
                    manufacturing_info();
                    break;
                }

                case "distribution info":
                {
                    distribution_info();
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
                    admin_main_page();
                }
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!!");
        }
    }

    public static void manage_admin_login_info()
    {
        try
        {
            String query, option, updatefield;

            ResultSet rs;
            
            query = "SELECT * FROM admin_info WHERE `Admin ID` = '"+username+"'";

            Login_signup.db_connect();

            rs = st.executeQuery(query);

            rs.next();

            System.out.println("\nAdmin ID: "+rs.getString(2));
            System.out.println("Admin Name: "+rs.getString(3));
            System.out.println("Admin Email: "+rs.getString(5));
            System.out.println("Admin Mobile: "+rs.getString(6));
            System.out.println("Date of Joining: "+rs.getString(7));
            System.out.println("Admin Department: "+rs.getString(8));

            System.out.println("\n[Update info | Exit to main page]");

            System.out.print("\nEnter your option: ");
            option = sc.nextLine();

            String arr1[] = {"update","update info","exit","exit to main page"};
                
            while (!Arrays.asList(arr1).contains(option.toLowerCase()))
            {
                System.out.print("\nOption Not found!\n\nEnter your option: ");
                option = sc.nextLine();
            }

            if (option.toLowerCase().equals("update") || option.toLowerCase().equals("update info"))
            {     
                System.out.println("\n[Name | Mobile | Email | Password | Department]");

                System.out.print("\nEnter the info to be updated: ");
                updatefield= sc.nextLine(); 

                updatefield = updatefield.toLowerCase();

                change_admin(updatefield);
            }

            else if (option.toLowerCase().equals("exit") || option.toLowerCase().equals("exit to main page"))
            {
                admin_main_page();   
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

    public static void change_admin(String updatefield)
    {      
        try
        {   
            String query, query1, newpass, update, dbpass, oldinfo = "", change_db = ""; 
        
            int i;

            ResultSet rs;
        
            System.out.print("\nEnter the new "+updatefield+": ");
            update = sc.nextLine();

            System.out.print("\nEnter your current password to change "+updatefield+": ");
            newpass = sc.nextLine();

            query = "SELECT `Admin Password`,`Admin Name`,`Admin Mobile`,`Admin Email`,`Admin Department` FROM admin_info WHERE `Admin ID` = '"+username+"'";

            Login_signup.db_connect();
            
            rs = st.executeQuery(query);

            rs.next();

            dbpass = rs.getString(1);
            
            for (i=0;i<=1;++i)
            {
                if (newpass.equals(dbpass))
                {
                    if (updatefield.equals("name") || updatefield.equals("admin name"))
                    {
                        change_db = "Name";
                        oldinfo = rs.getString(2);
                    }

                    else if (updatefield.equals("mobile") || updatefield.equals("admin mobile"))
                    {
                        change_db = "Mobile";
                        oldinfo = rs.getString(3);
                    }

                    else if (updatefield.equals("email") || updatefield.equals("e-mail") || updatefield.equals("admin email") || updatefield.equals("admin e-mail"))
                    {
                        change_db = "Email";
                        oldinfo = rs.getString(4);
                    }

                    else if (updatefield.equals("password") || updatefield.equals("admin password")) 
                    {
                        change_db = "Password";
                        oldinfo = newpass;
                    }
                    else if (updatefield.equals("department") || updatefield.equals("admin department")) 
                    {
                        change_db = "Department";
                        oldinfo = rs.getString(5);
                    }

                    if (update.equals(oldinfo))
                    {
                        System.out.println("\n Updating "+change_db+" cannot be the same as existing "+updatefield+"! ");

                        manage_admin_login_info();
                    }

                    else
                    {
                        query1 = "UPDATE admin_info SET `Admin "+change_db+"` = '"+update+"' WHERE `Admin ID` = '"+username+"'";
                        
                        Login_signup.db_update(query1);
                        
                        System.out.println("\n"+updatefield+" Updated Successfully!");

                        manage_admin_login_info();
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
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void manage_user_info()
    {
        try
        {
            String query, query1, admin_upd_username, admin_newpass, admin_dbpass, user_dbpass; 
        
            int i;
        
            ResultSet rs, rs1;

            System.out.print("\nEnter the username to view: ");
            admin_upd_username = sc.nextLine();

            System.out.print("\nEnter your password to view user info: ");
            admin_newpass = sc.nextLine();

            query = "SELECT `Admin Password` FROM admin_info WHERE `Admin ID` = '"+username+"'";

            query1= "SELECT Password FROM user_info WHERE Username = '"+admin_upd_username+"'";

            Login_signup.db_connect();
            
            rs = st.executeQuery(query);

            rs.next();
            
            admin_dbpass = rs.getString(1);
            
            for (i=0;i<=1;i++)
            {
                if (admin_newpass.equals(admin_dbpass))
                {
                    rs1 = st.executeQuery(query1);

                    if (rs1.next())
                    {
                        user_dbpass = rs1.getString(1);

                        User_methods.manage_my_info(admin_upd_username,user_dbpass);
                    }

                    else
                    {
                        System.out.println("User not found! ");

                        manage_user_info();
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
                    System.out.println("Incorrect password! Try again!!");
                }
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch (Exception e)
        {
            System.out.println("An Error Occured!");
        }
    }

    public static void raw_materials_info()
    {
        try
        {
            String option;

            System.out.println("\n[View Raw Materials Info | Update Raw Materials Info | Exit]");
           
            System.out.print("\nEnter your option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("view raw materials info") || option.toLowerCase().equals("view"))
            {
                view_raw_materials_info();
            }

            else if (option.toLowerCase().equals("update raw materials info") || option.toLowerCase().equals("update"))
            {
                update_raw_materials_info();
            }

            else if (option.toLowerCase().equals("exit"))
            {
                admin_main_page();
            }

            else
            {
                System.out.println("Invalid Input!");

                raw_materials_info();
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch (Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void view_raw_materials_info()
    {
        try
        {
            String query;
            
            boolean exists;

            ResultSet rs;
            
            query = "SELECT * FROM raw_materials_info";
            
            Login_signup.db_connect();

            rs = st.executeQuery(query);
            
            exists = Login_signup.check(query);

            if (exists)
            {
                while (rs.next())
                {
                    System.out.println("\nMaterial ID: "+rs.getInt(1));
                    System.out.println("Material Name: "+rs.getString(2));
                    System.out.println("Density of the Material: "+rs.getFloat(3));
                    System.out.println("Cost of the Material: "+rs.getInt(4));
                    System.out.println("Cost of Purchase: "+rs.getInt(5));
                    System.out.println("Availability: "+rs.getString(6));
                    System.out.println("Date of Purchase: "+rs.getString(7));
                    System.out.println("Condition of Material: "+rs.getString(8));
                    System.out.println("Quantity: "+rs.getInt(9)+"\n");
                }
            }

            else
            {
                System.out.println("\nNo Raw Material Records!!\n");
            }

            raw_materials_info();        
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

    public static void update_raw_materials_info()
    {
        try
        {
            String option;

            System.out.println("\n[Add new raw material | update existing material info | Exit]");
           
            System.out.print("\nEnter your option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("add new raw material") || option.toLowerCase().equals("add"))
            {
                add_new_raw_material();
            }

            else if (option.toLowerCase().equals("update existing material info") || option.toLowerCase().equals("update"))
            {
                update_existing_raw_materials_info();
            }

            else if (option.toLowerCase().equals("exit"))
            {
                raw_materials_info();
            }

            else
            {
                System.out.println("\nInvalid Input!");

                update_raw_materials_info();
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void add_new_raw_material()
    {
        try
        {
            String add_material, query, query1;

            int costperitem, purchasecost, quantity;

            Float density;

            LocalDateTime dateofpurchase;

            boolean exists;

            System.out.print("\nEnter the Raw material to be added: ");
            add_material = sc.nextLine();

            query = "SELECT * FROM raw_materials_info WHERE `Material Name` = '"+add_material+"'";

            exists = Login_signup.check(query);
            
            if (exists)
            {
                System.out.println("\nItem Already Exists!");
            }

            else
            {
                System.out.print("\nEnter the Density of "+add_material+": ");
                density = sc.nextFloat();

                System.out.print("Enter Cost per Item: ");
                costperitem = sc.nextInt();

                System.out.print("Enter cost of Purchase: ");
                purchasecost = sc.nextInt();

                dateofpurchase = LocalDateTime.now();

                System.out.print("Material ");

                System.out.print("Quantity: ");
                quantity = sc.nextInt();
            
                query1 = "INSERT INTO raw_materials_info (`Material Name`,`Density (Kg/m3)`,`Cost per item`,`Cost of Purchase`,`Availability`,`Date of Purchase`,`Condition of Material`,Quantity) Values ('"+add_material+"','"+density+"','"+costperitem+"','"+purchasecost+"','Available','"+dateofpurchase+"','Excellent','"+quantity+"');";
            
                Login_signup.db_update(query1);

                System.out.println("\n New Material '"+add_material+"' added Successfully!!");
            }

            update_raw_materials_info();
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

    public static void update_existing_raw_materials_info()
    {
        try
        {
            String updatematerial, updatefield = "", updatevalue = "", option, query = "";

            int updatevalue1;

            Float updatevalue2;

            boolean exists;

            System.out.print("\nEnter the name of the Material to be updated: ");
            updatematerial = sc.nextLine();

            query = "SELECT * FROM raw_materials_info WHERE `Material Name` = '"+updatematerial+"'";

            exists = Login_signup.check(query);

            if (exists)
            {
                System.out.println("\nEnter the field to be updated: [Density | Cost per Item | Cost of Purchase | Availability | Condition of Material | Quantity]");

                System.out.print("\nEnter your option: ");
                option = sc.nextLine();

                String arr[] = {"density","cost per item","cost of purchase","availability", "condition of material", "condition", "quantity"};
                
                while (!Arrays.asList(arr).contains(option.toLowerCase()))
                {
                    System.out.print("\nOption Not found!\n\nEnter your option: ");
                    option = sc.nextLine();
                }

                System.out.print("\nEnter the new value: ");

                if (option.toLowerCase().equals("density"))
                {
                    updatefield = "Density (Kg/m3)";

                    updatevalue2 = sc.nextFloat();

                    query = "UPDATE raw_materials_info SET `"+updatefield+"` = '"+updatevalue2+"' WHERE `Material Name` = '"+updatematerial+"';" ;
                }

                else if (option.toLowerCase().equals("cost per item"))
                {
                    updatefield = "Cost per item";

                    updatevalue1 = sc.nextInt();

                    query = "UPDATE raw_materials_info SET `"+updatefield+"` = '"+updatevalue1+"' WHERE `Material Name` = '"+updatematerial+"';" ;
                }

                else if (option.toLowerCase().equals("cost of purchase"))
                {
                    updatefield = "Cost of Purchase";

                    updatevalue1 = sc.nextInt();

                    query = "UPDATE raw_materials_info SET `"+updatefield+"` = '"+updatevalue1+"' WHERE `Material Name` = '"+updatematerial+"';" ;

                }

                else if (option.toLowerCase().equals("availability"))
                {
                    updatefield = "Availability";

                    updatevalue = sc.nextLine();

                    query = "UPDATE raw_materials_info SET `"+updatefield+"` = '"+updatevalue+"' WHERE `Material Name` = '"+updatematerial+"';" ;
                }

                else if (option.toLowerCase().equals("condition of material") || option.toLowerCase().equals("condition"))
                {
                    updatefield = "Condition of Material";

                    updatevalue = sc.nextLine();
                }

                else if (option.toLowerCase().equals("quantity"))
                {
                    updatefield = "Quantity";

                    updatevalue1 = sc.nextInt();

                    query = "UPDATE raw_materials_info SET `"+updatefield+"` = '"+updatevalue+"' WHERE `Material Name` = '"+updatematerial+"';" ;
                }

                Login_signup.db_update(query);

                System.out.println("\n"+updatefield+" of "+updatematerial+" has been updated successfully!");

                update_raw_materials_info();
            }

            else
            {
                System.out.println("Material Not found!");

                update_existing_raw_materials_info();
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
    
    public static void supply_info()
    {
        try
        {
            String option, option1;
        
            System.out.println("\n[View Supply info | Place Supply Order | Exit]");
       
            System.out.print("\nEnter your option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("view supply info") || option.toLowerCase().equals("supply info"))
            {
                view_supply_info();
            }

            else if (option.toLowerCase().equals("place supply order") || option.toLowerCase().equals("place order"))
            {
                System.out.println("\n[Enter Order Details | Exit]");

                System.out.print("\nEnter your Option: ");
                option1 = sc.nextLine();

                if (option1.toLowerCase().equals("enter order details") || option1.toLowerCase().equals("enter details"))
                {
                    enter_supply_order_details();
                }

                else if (option1.toLowerCase().equals("exit"))
                {
                    supply_info();
                }
            }

            else if (option.toLowerCase().equals("exit"))
            {
                admin_main_page();
            }

            else
            {
                System.out.println("\nInvalid Input!");
                supply_info();
            }
        }

        catch(IndexOutOfBoundsException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void view_supply_info()
    {
        try
        {
            String query;

            boolean exists;

            ResultSet rs;

            query = "SELECT * FROM supply_info";
            
            Login_signup.db_connect();

            rs = st.executeQuery(query);

            exists = Login_signup.check(query);

            if (exists)
            {
                while(rs.next())
                {
                    System.out.println("\nSupply ID: "+rs.getInt(1));
                    System.out.println("Material Name: "+rs.getString(2));
                    System.out.println("Time of Supply: "+rs.getString(3));
                    System.out.println("Cost of Material Supplied: Rs."+rs.getInt(4)+"/-");
                    System.out.println("Payment Status: "+rs.getString(5));
                    System.out.println("Supply Company Name: "+rs.getString(6));
                    System.out.println("Quantity: "+rs.getInt(7));
                    System.out.println("Date of Order: "+rs.getString(8));
                    System.out.println("Supply Status: "+rs.getString(9)+"\n");
                }             
            }

            else
            {
                System.out.println("\nNo Active Supply Orders!!\nPlace orders before viewing supply orders\n");
            }

            supply_info();
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch(Exception e)
        {
            System.out.println("An Error Occured!!");
        }
    }

    public static void enter_supply_order_details()
    {
        try
        {
            String orderitem = "", query, query1, arr1[] = {}, shape, companyname = "";

            int number,i, quantity=0 ,materialcost=0, totalbill = 0;

            float height, width, breadth, diameter, radius, volume=0, density=0, cost =0, weight=0;

            boolean exists;
            
            LocalDateTime ordertime;
            
            ResultSet rs;

            System.out.print("\nEnter the number of items to be ordered: ");
            number = sc.nextInt();
            
            for (i=0;i<=number-1;++i)
            {
                System.out.println("\nType done to proceed to Checkout! ");

                System.out.print("\nEnter the Item to be Ordered: ");
                orderitem = sc.nextLine();

                if (orderitem.toLowerCase().equals(""))
                {
                    orderitem = sc.nextLine();
                }

                if (orderitem.toLowerCase().equals("done"))
                {
                    break;
                }
    
                Login_signup.db_connect();

                query = "SELECT * FROM raw_materials_info WHERE `Material Name` = '"+orderitem+"'";

                exists = Login_signup.check(query);

                while (!exists)
                {
                    System.out.println("\nItem Not Available!");

                    System.out.print("\nEnter the item to be ordered: ");
                    orderitem = sc.nextLine();

                    exists = Login_signup.check(query);
                }

                rs = st.executeQuery(query);

                rs.next();

                if (exists)
                {
                    System.out.print("\nEnter Supply Company Name: ");
                    companyname = sc.nextLine();
                    
                    System.out.print("\n[Rectangular | Cylinder]\n\nEnter your Option: ");
                    shape = sc.nextLine();

                    System.out.print("\nQuantity of same material ("+orderitem+"): ");
                    quantity = sc.nextInt();
                    
                    System.out.println("\nMaterial "+(i+1)+" ("+orderitem+")");

                    density = rs.getFloat(3) * (float) Math.pow(10,-3);

                    materialcost = rs.getInt(4);

                    System.out.println("\nCost For 1000 cubic cm: Rs. "+materialcost+"/-");

                    System.out.println("\nEnter Dimensions (in mm): ");
                    
                    if (shape.toLowerCase().equals("rectangular"))
                    {
                        System.out.print("Height: ");
                        height = sc.nextInt();

                        System.out.print("Breadth: ");
                        breadth = sc.nextInt();

                        System.out.print("Thickness: ");
                        width = sc.nextInt();

                        volume = height*width*breadth;
                    }

                    else if (shape.toLowerCase().equals("cylinder"))
                    {
                        System.out.print("Height: ");
                        height = sc.nextInt();

                        System.out.print("Diameter: ");
                        diameter = sc.nextInt();

                        radius = diameter/2;

                        volume = (float) ((3.14)*((radius)*(radius))*(height));
                    }

                    weight = volume * density;

                    weight = weight * (float) Math.pow(10,-3);

                    cost = weight * materialcost;

                    System.out.println("\nVolume: "+volume+" (mm3)\nCost per Kg: Rs."+materialcost+"/-\nDensity of "+orderitem+": "+density+" (g/mm3)\nWeight of given dimension: "+weight+" (Kg)\nCost of purchase material: Rs."+cost+"/-");
                                        
                    totalbill += cost*quantity;

                    ordertime = LocalDateTime.now();

                    query1 = "INSERT INTO supply_info(`Material Name`,`Time of Supply (hrs)`,`Cost of Purchase`,`Payment Status`,`Supply Company Name`,`Material Quantity`,`Date of Order`,`Supply Status`) VALUES('"+orderitem+"','48','"+cost+"','Pending','"+companyname+"','"+quantity+"','"+ordertime+"','Pending');";

                    Login_signup.db_update(query1);

                    System.out.println("Material "+ (i+1)+" Order Recieved!");

                    ArrayList <String> additem = new ArrayList<String>(Arrays.asList(arr1));
                
                    additem.add("Material Name ("+orderitem+") : Volume ("+volume+") : Quantity ("+quantity+") : Cost ("+cost*quantity+")");

                    arr1 = additem.toArray(new String [0]);
                }
            }

            System.out.println("\nThe Ordered Items are: \n");

            if (arr1.length == 0)
            {
                System.out.print("\nNull\n");
                supply_info();
            }

            else
            {
                for (i=0;i<arr1.length;++i)
                {
                    System.out.println((i+1)+") "+arr1[i]);
                }
                
                System.out.println("\nThe Total Bill is: Rs."+totalbill+"/-");

                System.out.println("\nPayment will be Accepted at the time of delivery!");

                supply_info();
            }
        }

        catch(InputMismatchException i)
        {
            System.out.println("\nInvalid Input!");
        }

        catch (Exception e)
        {
            System.out.println("\nAn Error Occured!");
        }
    }

    public static void manufacturing_info()
    {
        try
        {
            String option;

            System.out.println("\n[View Manufacturing Info | Add New Manufacturing Order | Exit]");
            
            System.out.print("\nEnter your option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("view manufacturing info") || option.toLowerCase().equals("view"))
            {
                view_manufacturing_info();
            }

            else if (option.toLowerCase().equals("add new manufacturing order") || option.toLowerCase().equals("add"))
            {
                add_manufacturing_info();
            }

            else if (option.toLowerCase().equals("exit"))
            {
                admin_main_page();
            }

            else
            {
                System.out.println("\nInvalid Input");

                manufacturing_info();
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

    public static void view_manufacturing_info()
    {
        try
        {
            String query;
        
            boolean exists;

            ResultSet rs;

            Login_signup.db_connect();

            query = "SELECT * FROM manufacturing_info";

            rs = st.executeQuery(query);

            exists = Login_signup.check(query);

            if (exists)
            {
                while (rs.next())
                {
                    System.out.println("\nManufacturing ID: "+rs.getInt(1));
                    System.out.println("Product ID: "+rs.getString(2));
                    System.out.println("Customer Username: "+rs.getString(3));
                    System.out.println("Component Name: "+rs.getString(4));
                    System.out.println("Quantity: "+rs.getInt(11));                
                    System.out.println("Type of Production: "+rs.getString(5));
                    System.out.println("Date of Manufacturing: "+rs.getString(6));
                    System.out.println("Date of Final Assemly: "+rs.getString(7));
                    System.out.println("Manufacturing Status: "+rs.getString(8));
                    System.out.println("Dispatch Status: "+rs.getString(9));
                    System.out.println("Cost of Manufacturing: "+rs.getInt(10)+"\n");
                }
            }

            else
            {
                System.out.println("\nNo Active Manufacturing Orders!!");
            }

            manufacturing_info();
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

    public static void add_manufacturing_info()
    {
        try
        {
            String queryproductid, querycustomerusername, querycomponentname, productid, customerusername, componentname="", productiontype, manufacturingstatus, dispatchstatus;

            int costofmanufacturing=0, quantity, totalcostofmanufacturing;

            boolean productidexists, customerusernameexists;

            LocalDateTime manufacturingdate, dateoffinalassembly;

            ResultSet rs1;

            System.out.print("\nEnter Product ID: ");
            productid = sc.nextLine();

            Login_signup.db_connect();

            queryproductid = "SELECT * FROM product_info WHERE `Product ID` = '"+productid+"';";

            productidexists = Login_signup.check(queryproductid);
            
            while (!productidexists)
            {
                System.out.println("\nProduct does not exist!");
                System.out.print("\nEnter Product ID: ");
                productid = sc.nextLine();

                queryproductid = "SELECT * FROM product_info WHERE `Product ID` = '"+productid+"';";

                productidexists = Login_signup.check(queryproductid);
            }

            System.out.print("\nEnter Customer username: ");
            customerusername = sc.nextLine();

            querycustomerusername = "SELECT * FROM user_info WHERE Username = '"+customerusername+"';";

            customerusernameexists = Login_signup.check(querycustomerusername);

            while (!customerusernameexists)
            {
                System.out.println("\nInvalid Customer Username!");
                System.out.print("\nEnter Customer username: ");
                customerusername = sc.nextLine();

                querycustomerusername = "SELECT * FROM user_info WHERE Username = '"+customerusername+"';";

                customerusernameexists = Login_signup.check(querycustomerusername);
            }

            querycomponentname = "SELECT * FROM product_info WHERE `Product ID` = '"+productid+"';";

            rs1 = st.executeQuery(querycomponentname);

            if (rs1.next())
            {
                componentname = rs1.getString(2);
                costofmanufacturing = rs1.getInt(4);
            }

            productiontype = "Batch";

            manufacturingdate = LocalDateTime.now();

            manufacturingstatus = "Order Recieved";

            dispatchstatus = "null";

            System.out.print("\nEnter the Quantity to Order: ");
            quantity = sc.nextInt();

            totalcostofmanufacturing = costofmanufacturing*quantity;

            dateoffinalassembly = LocalDateTime.now().plusDays(7*quantity);

            add_manu_info_to_database(productid, customerusername, componentname, productiontype, manufacturingdate, dateoffinalassembly, manufacturingstatus, dispatchstatus, totalcostofmanufacturing, quantity);
        
            System.out.println("\nOrder Recieved Successfully!");

            System.out.println("\nView Manufacturing Info to know more details!");

            manufacturing_info();
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

    public static void add_manu_info_to_database(String productid, String customerusername, String componentname, String productiontype, LocalDateTime manufacturingdate, LocalDateTime dateoffinalassembly, String manufacturingstatus, String dispatchstatus, int totalcostofmanufacturing, int quantity)
    {
        try
        {
            String query;

            query = "INSERT INTO manufacturing_info (`Product ID`,`Username`,`Component Name`,`Type of Production`,`Date of Manufacturing`,`Date of Final Assembly`,`Manufacturing Status`,`Dispatch Status`,`Cost of Manufacturing`,Quantity)Values('"+productid+"','"+customerusername+"','"+componentname+"','"+productiontype+"','"+manufacturingdate+"','"+dateoffinalassembly+"','"+manufacturingstatus+"','"+dispatchstatus+"','"+totalcostofmanufacturing+"','"+quantity+"');";

            Login_signup.db_update(query);
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

    public static void distribution_info()
    {
        try
        {
            String option;

            System.out.println("\n[View Distribution Info | Add Distribution Info | Exit]");

            System.out.print("\nEnter your option: ");
            option = sc.nextLine();

            if (option.toLowerCase().equals("view distribution info") || option.toLowerCase().equals("view"))
            {
                view_distribution_info();
            }

            else if (option.toLowerCase().equals("add distribution info") || option.toLowerCase().equals("add"))
            {
                add_distribution_info();
            }

            else if (option.toLowerCase().equals("exit"))
            {
                admin_main_page();
            }

            else
            {
                System.out.println("\nInvalid Input!!");

                distribution_info();
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

    public static void view_distribution_info()
    {
        try
        {
            String query;
        
            boolean exists;
        
            ResultSet rs;

            Login_signup.db_connect();

            query = "SELECT * FROM distribution_info";

            rs = st.executeQuery(query);

            exists = Login_signup.check(query);

            if (exists)
            {
                while(rs.next())
                {
                    System.out.println("\nOrder ID: "+rs.getInt(1));
                    System.out.println("Username: "+rs.getString(2));
                    System.out.println("Product Id: "+rs.getString(3));
                    System.out.println("Order Date: "+rs.getString(4));
                    System.out.println("Distribution Date: "+rs.getString(5));
                    System.out.println("Distribution Status: "+rs.getString(6));
                    System.out.println("Cost: "+rs.getInt(7));
                    System.out.println("Payment Status: "+rs.getString(8)+"\n");
                }
            }

            else
            {
                System.out.println("\nNo Active Distribution!!");
            }
            
            distribution_info();
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

    public static void add_distribution_info()
    {
        try
        {
            String queryorderid, customerusername, querycustomerusername, productid, queryproductid, queryorderdate, querydistributiondate;

            boolean orderidexists, customerusernameexists, productidexists;

            int orderid, cost;

            LocalDateTime orderdate, finalassemblydate;

            ResultSet rs1, rs2;

            Login_signup.db_connect();

            System.out.print("\nEnter Order ID: ");
            orderid = sc.nextInt();

            queryorderid = "SELECT * FROM distribution_info WHERE `Order ID` = '"+orderid+"';";

            orderidexists = Login_signup.check(queryorderid);

            while(orderidexists)
            {
                System.out.println("\nDistribution information is already available for this Order!");

                System.out.print("\nEnter Order ID: ");
                orderid = sc.nextInt();

                orderidexists = Login_signup.check(queryorderid);
            }

            System.out.print("\nEnter Customer username: ");
            customerusername = sc.nextLine();

            if(customerusername.equals(""))
            {
                System.out.print("");
                customerusername = sc.nextLine();
            }

            querycustomerusername = "SELECT * FROM user_info WHERE Username = '"+customerusername+"';";

            customerusernameexists = Login_signup.check(querycustomerusername);

            while (!customerusernameexists)
            {
                System.out.println("\nInvalid Customer Username!");
                System.out.print("\nEnter Customer username: ");
                customerusername = sc.nextLine();

                customerusernameexists = Login_signup.check(querycustomerusername);
            }

            System.out.print("\nEnter Product ID: ");
            productid = sc.nextLine();

            queryproductid = "SELECT * FROM product_info WHERE `Product ID` = '"+productid+"';";

            productidexists = Login_signup.check(queryproductid);
            
            while (!productidexists)
            {
                System.out.println("\nProduct does not exist!");
                System.out.print("\nEnter Product ID: ");
                productid = sc.nextLine();

                productidexists = Login_signup.check(queryproductid);
            }

            queryorderdate = "SELECT * FROM customer_order_info WHERE `Order ID` = '"+orderid+"';";

            rs1 = st.executeQuery(queryorderdate);

            rs1.next();

            orderdate = (LocalDateTime) rs1.getObject(3);

            querydistributiondate = "SELECT * FROM manufacturing_info WHERE `Manufacturing ID` = '"+orderid+"';";

            rs2 = st.executeQuery(querydistributiondate);

            rs2.next();

            finalassemblydate = (LocalDateTime) rs2.getObject(7);

            cost = rs2.getInt(10);

            add_dist_info_to_database(orderid,customerusername,productid,orderdate,finalassemblydate,cost);

            System.out.println("\nOrder Recieved Successfully!");

            System.out.println("\n View Distribution Info to know more details!");

            distribution_info();
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

    public static void add_dist_info_to_database(int orderid, String customerusername, String productid, LocalDateTime orderdate, LocalDateTime finalassemblydate, int cost)
    {
        String query;

        try
        {
            query = "INSERT INTO distribution_info (`Order ID`,`Username`,`Product ID`,`Order Date`,`Scheduled Distribution Date`,`Cost`)Values('"+orderid+"','"+customerusername+"','"+productid+"','"+orderdate+"','"+finalassemblydate+"','"+cost+"');";

            Login_signup.db_update(query);
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
}
