
import mysql.connector
import covid_admin_inputs

covid_db=mysql.connector.connect(
    host="localhost",
    user="root",
    password="12345",
    database="covid_data"
)

cursor=covid_db.cursor()

def admin_login():

    admin_id=input("Enter your ID: ")
    admin_password=input("Enter your Password: ")

    sql_login = "SELECT `Admin Password` FROM admin_database WHERE `Admin ID` = %s"
    
    cursor.execute(sql_login, (admin_id,))
    result = cursor.fetchone()

    if result is not None and result [0] == admin_password :

        print("\nAdmin Login Successfull\n")

        covid_admin_inputs.admin_actions()

    else:

        print("The Username / Password is invalid\n")
        
        admin_login()
