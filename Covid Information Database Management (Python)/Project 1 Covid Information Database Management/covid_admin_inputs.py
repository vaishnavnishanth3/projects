
import covid_main_page
import database
import random
import smtplib


def insert_medical_data():
    
    s_no=random.randint(00,99)
    name=input("Enter Patient Name: ")
    age=input("Enter Patient Age: ")
    testtype=input("Enter the Test type: ")
    testdate=input("Enter the Date of test: ")
    doneby=input("Test Done by: ")
    result=input("Test Result: ").upper()
    amount=input("Enter the Amount paid: Rs.")
    
    sql_insert="insert into medical_database (S_no,Name,Age,`Test Type`,`Test Date`,`Test Done by`,`Test Result`,`Amount paid for Test`) values (%s,%s,%s,%s,%s,%s,%s,%s)"
    val=(s_no,name,age,testtype,testdate,doneby,result,amount)
    
    database.commit_db(sql_insert,val)
                       
    print("\nData Inserted!!\n")


def insert_vaccination_data():

    s_no=random.randint(00,99)
    name=input("Enter Patient Name: ")
    age=input("Enter Patient Age: ")
    vaccination_status=input("Enter your Vaccination Status: ")
    vaccination_name=input("Enter Vaccine Name: ")
    vaccination_date=input("Enter Vaccination Date: ")
    payment=input("Enter payment method: ")

    sql_vaccination_data="insert into vaccination_data (S_no,Name,Age,`Vaccination Status`,`Vaccine Name`,`Vaccination Date`,`Payment for vaccine`) values (%s,%s,%s,%s,%s,%s,%s)"
    val=(s_no,name,age,vaccination_status,vaccination_name,vaccination_date,payment)
    
    database.commit_db(sql_vaccination_data,val)

    print("\nData Inserted!!\n")


def insert_death_data():

    s_no=random.randint(00,99)
    name=input("Enter Name: ")
    age=input("Enter Age: ")
    gender=input("Enter Gender: ")
    death_date=input("Enter death date: ")
    death_time=input("Enter death time: ")
    vaccination_status=input("Enter the vaccination status: ")
    city=input("Enter the city: ")
    treatments_status=input("Enter the treatment Status: ")

    sql_death_data="insert into death_details (`S.No`,Name,Age,Gender,`Death Date`,`Death Time`,`Vaccination Status`,`City`,`Treatment Status`) values (%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    val=(s_no,name,age,gender,death_date,death_time,vaccination_status,city,treatments_status)
    
    database.commit_db(sql_death_data,val)

    print("\nData Inserted!!\n")
    

def update_test_result():

    reference_value=input("Enter the Name: ")
    change_data=input("Modified Data: ")
    sql_update_result="update medical_database set `Test Result` = %s where Name = %s"
    val=(change_data,reference_value)

    database.commit_db(sql_update_result,val)

    print("Data Updated!!")


def email_sending():

    login_maidid=input("Enter the Login Mail ID: ")
    login_mailid_password=input("Enter the login Mail Password: ")
    reciever_mailid=input("Enter the Reciever Id: ")
    message = input("Enter the Message to be sent: ")
    s = smtplib.SMTP('smtp.gmail.com', 587)

    s.starttls()
    s.login(login_maidid, login_mailid_password)
    s.sendmail(login_maidid, reciever_mailid , message)
    s.quit()

    print("\nEmail Sent!!\n")


def navigate_option_admin():

    print("\n[ Continue | Logout ]")
    print("Input 1 --> Continue")
    print("Input 2 --> Logout")

    option1=int(input("Enter your input: "))

    if option1==1:
        admin_actions()

    if option1==2:
        covid_main_page.mainpage()

    else:
        navigate_option_admin()


def admin_actions():

    print("\n[ View Appointment Schedule | View Login Data | View Medical Data | View Active Cases | View Vaccination Details | View Death detail ]")
    print("                      [ Insert Medical Data | Insert Vaccination Data | Insert Death Data | Update Test Result Data ]")
    print("                                                             [ Send Email ]")
    print("                                                               [ Log-out ]\n")
    print("Input  1 --> View Appointment Schedule\nInput  2 --> View Login Data\nInput  3 --> View Medical Data\nInput  4 --> View Active Cases\nInput  5 --> View Vaccination Details\nInput  6 --> View Death details\nInput  7 --> Insert Medical Data\nInput  8 --> Insert Vaccination Data\nInput  9 --> Insert Death Data\nInput 10 --> Update Test Result Data\nInput 11 --> Send Email\nInput 12 --> Log-out\n")

    access_option_2=int(input("Enter the input: "))

    if access_option_2==1:

        sql_view_appointment_db="SELECT * FROM appointment_registration;"
        
        database.execute_db(sql_view_appointment_db)

        navigate_option_admin()

    elif access_option_2==2:

        sql_view_login_db="SELECT * FROM login_database;"

        database.execute_db(sql_view_login_db)

        navigate_option_admin()

    elif access_option_2==3:

        sql_view_medical_data="SELECT * FROM medical_database;"

        database.execute_db(sql_view_medical_data)

        navigate_option_admin()

    elif access_option_2==4:

        sql_view_active="SELECT * FROM medical_database where `Test Result` = 'POSITIVE' ;"

        database.execute_db(sql_view_active)

        navigate_option_admin()

    elif access_option_2==5:

        sql_view_vaccine="SELECT * FROM vaccination_data"

        database.execute_db(sql_view_vaccine)

        navigate_option_admin()

    elif access_option_2==6:

        sql_view_death="SELECT * FROM death_details"

        database.execute_db(sql_view_death)

        navigate_option_admin()

    elif access_option_2==7:

        insert_medical_data()
        
        navigate_option_admin()

    elif access_option_2==8:

        insert_vaccination_data()

        navigate_option_admin()

    elif access_option_2==9:

        insert_death_data()

        navigate_option_admin()

    elif access_option_2==10:

        update_test_result()

        navigate_option_admin()

    elif access_option_2==11:

        email_sending()

        navigate_option_admin()

    elif access_option_2==12:

        print("Logged Out")

        covid_main_page.mainpage()

    else:

        print("Invalid inputs")
        
        admin_actions()
