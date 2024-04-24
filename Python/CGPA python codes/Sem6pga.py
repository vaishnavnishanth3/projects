#sem6:

print("Input must be in numbers")
print("O  -- 10")
print("A+ -- 09")
print("A  -- 08")
print("B+ -- 07")
print("B  -- 06\n")

print("Sem 6:\n")
#credits:
cs6p1=3
cs6p2=3
cs6p3=3
cs6p4=3
cs6p5=3
cs6p6=3
cs6p7=2
cs6p8=1
cs6p9=2

#scores:
s6p1=int(input("Enter Score for Sub 1                 : "))
s6p2=int(input("Enter Score for Sub 2                 : "))
s6p3=int(input("Enter Score for Sub 3                 : "))
s6p4=int(input("Enter Score for Sub 4                 : "))
s6p5=int(input("Enter Score for Sub 5                 : "))
s6p6=int(input("Enter Score for Professional Elective : "))
s6p7=int(input("Enter Score for Lab 1                 : "))
s6p8=int(input("Enter Score for Lab 2                 : "))
s6p9=int(input("Enter Score for Naan Mudhalvan        : "))

#credit*score
css6p1=s6p1*cs6p1
css6p2=s6p2*cs6p2
css6p3=s6p3*cs6p3
css6p4=s6p4*cs6p4
css6p5=s6p5*cs6p5
css6p6=s6p6*cs6p6
css6p7=s6p7*cs6p7
css6p8=s6p8*cs6p8

print("\n",css6p1,css6p2,css6p3,css6p4,css6p5,css6p6,css6p7,css6p8,"\n")

s6ssum=css6p1+css6p2+css6p3+css6p4+css6p5+css6p6+css6p7+css6p8
s6csum=cs6p1+cs6p2+cs6p3+cs6p4+cs6p5+cs6p6+cs6p7+cs6p8
gpas6=s6ssum/s6csum

print(f"Score Sum is {s6ssum}\n")

print(f"Credit Sum is {s6csum}\n")

print(f"GPA for Sem 6 is {gpas6}\n")
