
import covid_admin_log_in
import covid_user_login_and_signup

def mainpage():

    print("\n                        COVID INFORMATION DATABASE")
    print("\n                         [ Admin | User | Exit ]\n")
    print("Input 1 --> [ Admin ]\nInput 2 --> [ User ]\nInput 3 --> [ Close the Application ]")

    access_option=int(input(("Enter your option: ")))

    if access_option==1:

        covid_admin_log_in.admin_login()

    elif access_option==2:

        covid_user_login_and_signup.user_action()

    elif access_option==3:

        print("\nThank you for using this application.\n")

    else:
        print("\nInvalid Input!!\n")
        
        mainpage()
