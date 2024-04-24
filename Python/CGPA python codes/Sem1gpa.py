#Sem1:

print("\nInput must be in numbers")
print("O  -- 10")
print("A+ -- 09")
print("A  -- 08")
print("B+ -- 07")
print("B  -- 06\n")

print("Sem 1:\nCredits:")

#credits:
cs1p1=4
cs1p2=4
cs1p3=3
cs1p4=3
cs1p5=3
cs1p6=4
cs1p7=2
cs1p8=2

print(cs1p1,cs1p2,cs1p3,cs1p4,cs1p5,cs1p6,cs1p7,cs1p8,"\n")

print("Scores:")
#scores:
s1p1=int(input("Enter Score for Sub 1: "))
s1p2=int(input("Enter Score for Sub 2: "))
s1p3=int(input("Enter Score for Sub 3: "))
s1p4=int(input("Enter Score for Sub 4: "))
s1p5=int(input("Enter Score for Sub 5: "))
s1p6=int(input("Enter Score for Sub 6: "))
s1p7=int(input("Enter Score for Lab 1: "))
s1p8=int(input("Enter Score for Lab 2: "))
print("")

print("Credit*Scores:\n")
#credit*score
css1p1=s1p1*cs1p1
css1p2=s1p2*cs1p2
css1p3=s1p3*cs1p3
css1p4=s1p4*cs1p4
css1p5=s1p5*cs1p5
css1p6=s1p6*cs1p6
css1p7=s1p7*cs1p7
css1p8=s1p8*cs1p8

print(css1p1,css1p2,css1p3,css1p4,css1p5,css1p6,css1p7,css1p8,"\n")

s1ssum = css1p1 + css1p2 + css1p3 + css1p4 + css1p5 + css1p6 + css1p7 + css1p8
s1csum = cs1p1 + cs1p2 + cs1p3 + cs1p4 + cs1p5 + cs1p6 + cs1p7 + cs1p8
gpas1 = s1ssum / s1csum

print(f"Score Sum is {s1ssum}")
print("")
print(f"Credit Sum is {s1csum}")
print("")
print(f"GPA for Sem 1 is {gpas1}")
print("")
