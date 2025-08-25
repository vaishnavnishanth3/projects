import mysql.connector
import random
import datetime

atm_machine=mysql.connector.connect(
    host="localhost",
    user="root",
    password="12345",
    database="atm_machine"
)

cursor=atm_machine.cursor()


def register_card():

    s_no=random.randint(0,99)
    card_number=random.randint(0000000000000000,9999999999999999)
    account_type=input("Enter the Account Type: ")
    account_number=random.randint(00000000000,999999999999)
    pin=random.randint(0000,9999)
    name=input("Enter your name: ")
    email=input("Enter your email: ")
    mobile=int(input("Enter your Mobile Number: "))

    sql_register_card="insert into login_data (`S.No`,`Card Number`,`Account Type`,`Account Number`,Pin,Name,Email,Mobile) values (%s,%s,%s,%s,%s,%s,%s,%s)"
    vals=(s_no,card_number,account_type,account_number,pin,name,email,mobile)

    cursor.execute(sql_register_card,vals)
    atm_machine.commit()

    print("\nData Registered")


def insert_account_info():

    s_no=random.randint(0,99)
    account_number=input("Enter account number: ")
    pin=int(input("Enter the 4 digit pin: "))
    name=input("Enter Name: ")
    date_time=datetime.datetime.now()
    balance=input("Enter the First Deposit: ")
    last_transaction=datetime.datetime.now()

    sql_account_info="insert into account_details (`S.No`,`Account Number`,Pin,Name,`Account Created On (Date & Time)`,Balance,`Last Transaction`) values (%s,%s,%s,%s,%s,%s,%s)"
    vals=(s_no,account_number,pin,name,date_time,balance,last_transaction)
    
    cursor.execute(sql_account_info,vals)
    atm_machine.commit()

    print("\nAccount Info Inserted")

def view_account_info():

    query="select * from account_details"
    
    view(query)

def view_user_info():
    
    query="select * from login_data"
    
    view(query)

def view(query):

    cursor.execute(query)
    
    for data in cursor.fetchall():
        print(data)
    
    atm_machine.commit()

def action():
    
    print("\n[ Register User Info | View User Info | Register Account Info | View Account Info ]\nInput 1 --> Register User Info\nInput 2 --> View User Info\nInput 3 --> Register Account Info\nInput 4 --> View Account Info\nInput 5 --> Exit")

    option=int(input("Enter your Option: "))

    if option==1:

        register_card()

        action()

    elif option==2:

        view_user_info()

        action()

    elif option==3:

        insert_account_info()

        action()

    elif option==4:

        view_account_info()

        action()

    elif option==5:

        print("Logged-Out")

    else:

        print("Invalid Input!")

        action()

action()
