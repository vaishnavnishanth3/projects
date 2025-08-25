import mysql.connector
import datetime

atm_machine=mysql.connector.connect(
    host="localhost",
    user="root",
    password="12345",
    database="atm_machine"
)

cursor=atm_machine.cursor()


def welcome():

    print("\nWELCOME TO NATIONAL BANK ATM MACHINE\nType ESC --> Stop the pending previous transaction and Insert Your Debit Card!\nType EXIT --> Exit the ATM Interface.\n")

    option=input("Enter your Option: ")

    if option=="ESC":

        main_page()

    elif option=="EXIT":

        print("\nThanks for using National BANK ATM\n")

    else:

        print("Invalid Input!")

        welcome()


def main_page():    

    print("\nInsert Card: \nType YES --> if the card is inserted")

    insert_option=input("Enter option: ")

    if insert_option=='YES':

        print("\nDebit Card accepted!!")

        user()

def user():

    print("\n[ Register for New Account | Use ATM by Debit Card ]\nInput 1 --> Registration\nInput 2 --> Debit Card usage\nInput 3 --> Exit to Main Page\n")
    
    action_option=int(input("Enter your option: "))
    
    if action_option==1:

        print("\nVisit nearby bank to create a new account & obtin Debit card")

        welcome()

    elif action_option==2:

        credentials()
    
    elif action_option==3:

        welcome()

    else:

        print("Invalid Input")

        user()


def credentials():

    debit_card_number = input("Enter your Debit card number: ")
    debit_card_pin = input("Enter your Pin: ")

    sql_login = "SELECT Pin FROM login_data WHERE `Card Number` = %s"

    try:
        cursor.execute(sql_login, (debit_card_number,))
        result = cursor.fetchone()

        if result is not None and result[0] == debit_card_pin:

            print("Credentials verified")

            user_action()

        else:

            print("Invalid credentials!!")

            credentials()

    except mysql.connector.Error as err:

        print(f"Error: {err}")

    finally:

        cursor.close()


def user_action():

    print("\n[ View Balance | Withdraw Money | Deposit Money ]\nInput 1 --> View Balance\nInput 2 --> Withdraw Money\nInput 3 --> Deposit Money\nInput 4 --> Retrieve Card\n")

    option=int(input("Enter Your Input: "))

    if option==1:

        view_balance()

        user_action()

    elif option==2:

        withdraw_money()

        user_action()

    elif option==3:

        deposit_money()

        user_action()

    elif option==4:

        retrieve_card()

    else:

        print("\nInvalid Input!")

        user_action()


def view_balance():

    account_number=input("Enter the Account Number: ")

    sql_view_balance="SELECT Balance FROM account_details where `Account Number` = %s"
    cursor.execute(sql_view_balance,(account_number,))

    for data in cursor.fetchall()[0]:
        print(f"your current balance is Rs.{data}")

    atm_machine.commit()

def withdraw_money():

    try:

        account_number = input("Enter the Account Number: ")
        debit_card_pin = input("Enter your Pin: ")

        sql_login = "SELECT Pin FROM login_data WHERE `Account Number` = %s"
        cursor.execute(sql_login, (account_number,))
        result = cursor.fetchone()

        if result is not None and result[0] == debit_card_pin:
            
            sql_view_balance = "SELECT Balance FROM account_details WHERE `Account Number` = %s"
            cursor.execute(sql_view_balance, (account_number,))
            current_balance = cursor.fetchone()[0]

            debit_amount = float(input("Enter the Amount to be Debited / Withdrawn: "))
            
            if int(debit_amount) > 0 and int(debit_amount) < int(current_balance):
                
                new_balance = int(current_balance) - int(debit_amount)
                date_time = datetime.datetime.now()

                sql_withdrawal_update = "UPDATE account_details SET Balance = %s , `Last Transaction` = %s WHERE `Account Number` = %s"
                cursor.execute(sql_withdrawal_update, (new_balance,date_time,account_number))
                atm_machine.commit()

                print("\nWithdrawal Successful\n")

                print(f"Amount Rs.{debit_amount} has been debited from your account {account_number} on {date_time}. Your Current Balance is {new_balance}")
                
            else:
                
                print("Your Balance is lower than your debit_amount. Please enter a valid amount.")
                
                withdraw_money()
        
        else:
        
            print("Invalid credentials!!")

    except mysql.connector.Error as err:
       
        print(f"Error: {err}")

    finally:
       
        cursor.close()


def deposit_money():

    try:

        account_number = input("Enter the Account Number: ")
        debit_card_pin = input("Enter your Pin: ")

        sql_login = "SELECT Pin FROM login_data WHERE `Account Number` = %s"
        cursor.execute(sql_login, (account_number,))
        result = cursor.fetchone()

        if result is not None and result[0] == debit_card_pin:
            
            sql_view_balance = "SELECT Balance FROM account_details WHERE `Account Number` = %s"
            cursor.execute(sql_view_balance, (account_number,))
            current_balance = cursor.fetchone()[0]

            credit_amount = float(input("Enter the Amount to be Credited / Depostied: "))
            
            if int(credit_amount) > 0:
                new_balance = int(current_balance) + int(credit_amount)
                date_time = datetime.datetime.now()

                sql_withdrawal_update = "UPDATE account_details SET Balance = %s , `Last Transaction` = %s WHERE `Account Number` = %s"
                cursor.execute(sql_withdrawal_update, (new_balance, date_time, account_number))
                atm_machine.commit()

                print("Deposit Successful")

                print(f"Amount Rs.{credit_amount} has been Credited to your account {account_number} on {date_time}. Your Current Balance is {new_balance}")
                
            else:

                print("Invalid amount. Please enter a valid amount.")

                withdraw_money()

        else:

            print("Invalid credentials!!")

    except mysql.connector.Error as err:

        print(f"Error: {err}")

    finally:

        cursor.close()


def retrieve_card():

    print("\nCard Retrieved!! Don't Forget to Collect the Card!\n")

    welcome()
