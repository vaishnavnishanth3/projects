import covid_main_page
import covid_admin_inputs
import covid_user_login_and_signup
import database
import mysql.connector

covid_db=mysql.connector.connect(
    host="localhost",
    user="root",
    password="12345",
    database="covid_data"
) 

cursor=covid_db.cursor()


def navigate_opts_user():

    print("\n[ Continue | Logout ]\nInput 1 --> Continue\n\Input 2 --> Logout")

    option1=int(input("Enter your input: "))

    if option1==1:

        covid_user_login_and_signup.user_action()
        
    if option1==2:
        print("Logged Out!")

        covid_main_page.mainpage()

    else:
        navigate_opts_user()


def view_individual_medical_data():

    username=input("Enter your Username: ")

    sql_view_individual_medical_data="SELECT * FROM medical_database where Username = %s;"
    val = (username,)
    
    database.execute_db_user(sql_view_individual_medical_data,val)


def view_individual_login_data():

    username=input("Enter your Username: ")
    
    sql_view_individual_login_data="SELECT * FROM login_database where Username = %s;"
    val = (username,)

    database.execute_db_user(sql_view_individual_login_data,val)


def appointment_registration():
    
    print("\n*********WELCOME TO COVID SPREAD PREVENTION SERVICE************\nAppointment Registration: [ Register For Appointment | Exit ]\nInput 1 --> Register for appointment\nInput 2 --> Exit to user Home page")

    access_option4=int(input("Enter your option: "))

        
    if access_option4==1:
    
        register_data()
    
    elif access_option4==2:
    
        user_actions()
    
    else:
    
        print("Invalid Input")
    
        appointment_registration()


def register_data():

    name=input("Enter your Name: ")
    reason=input("Enter your Reason for Appointment: ")
    appointment_datetime=input("Enter the appointment data & time: ")
    fee=input("Enter the Consultancy Fee: ")
    update=input("Enter the update in time (if available): ")
    
    sql_registaration_data="insert into appointment_registration (Name,`Reason for Appointment`,`Appointment Date and Time`,`Consultancy Fee`,`Update in time (if any)`) values (%s,%s,%s,%s,%s)"
    val=(name,reason,appointment_datetime,fee,update)
    
    database.commit_db(sql_registaration_data,val)

    print("Registration successfull")


def user_actions():
    
    print("\n[ View Medical Data | View login data | Insert Medical Data | Insert Vaccination Data | Register for appointment ]")
    print("                                                     [ Log-out ]")
    
    print("Input 1 --> View Medical Data\nInput 2 --> View login data\nInput 3 --> Insert Medical Data\nInput 4 --> Insert Vaccination Data\nInput 5 --> Register for appointment\nInput 6 --> Log-out")
    
    access_option3=int(input("Enter your Option: "))
    
    if access_option3==1:

        view_individual_medical_data()

        navigate_opts_user()

    elif access_option3==2:

        view_individual_login_data()

        navigate_opts_user()    

    elif access_option3==3:

        covid_admin_inputs.insert_medical_data()

        navigate_opts_user()

    elif access_option3==4:

        covid_admin_inputs.insert_vaccination_data()

        navigate_opts_user()

    elif access_option3==5:

        appointment_registration()

        navigate_opts_user()

    elif access_option3==6:

        print("Logged-Out")

        covid_main_page.mainpage()

    else:

        print ("Invalid input!")

        user_actions()
        