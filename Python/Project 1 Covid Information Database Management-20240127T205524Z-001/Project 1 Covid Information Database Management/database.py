import mysql.connector

covid_db=mysql.connector.connect(
        host="localhost",
        user="root",
        password="12345",
        database="covid_data"
    )

cursor=covid_db.cursor()

def commit_db(query,var):

    cursor.execute(query,var)
    covid_db.commit()

def execute_db(query):

    cursor.execute(query,)

    for data in cursor.fetchall():
        print(data)

def execute_db_user(query,var):

    cursor.execute(query,var)

    data = cursor.fetchall()
    print(data)
