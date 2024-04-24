
import covid_user_inputs
import database
import mysql.connector
import random

covid_db=mysql.connector.connect(
    host="localhost",
    user="root",
    password="12345",
    database="covid_data"
)

cursor=covid_db.cursor()


def user_sign_up():

    s_no=random.randint(0000,9999)
    name=input("Enter your name: ")
    age=input("Enter your age: ")
    gender=input("Enter your gender [M/F/Others]: ")
    mobile=int(input("Enter your Mobile: "))
    email=input("Enter your Email: ")
    username=input("Enter your Username: ")
    password=input("Enter your password: ")

    sql_insert_data="insert into login_database (S_No, Username, Password, Name, Age, Gender, Mobile, Email) values (%s,%s,%s,%s,%s,%s,%s,%s)"
    
    val=(s_no,username,password,name,age,gender,mobile,email)
    
    database.commit_db(sql_insert_data,val)

    print("\nSign Up Successfull!")


def user_log_in():

    login_username=input("Enter your username: ")
    login_password=input("Enter your password: ")

    sql_login = "SELECT Password FROM login_database WHERE Username = %s"

    cursor.execute(sql_login, (login_username,))
    result = cursor.fetchone()

    if result is not None and result [0] == login_password :
        
        print("\nLogin Successfull\n")

        covid_user_inputs.user_actions()

    else:

        print("The Username / Password is invalid")
        user_log_in()
            

def user_action():

    print("\n                                     [ Sign Up | Login ]")
    print("Input 1 --> [ Sign-Up ]")
    print("Input 2 --> [ Login ]")

    signin_option=int(input("Enter your option: "))

    if signin_option==1:

        user_sign_up()
        
        user_action()

    elif signin_option==2:
        
        user_log_in()

    else:

        print("Invalid input!")

        user_action()
        