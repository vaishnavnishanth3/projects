#sem4:
print("Input must be in numbers")
print("O  -- 10")
print("A+ -- 09")
print("A  -- 08")
print("B+ -- 07")
print("B  -- 06\n")

print("Sem 4:\n")
#credits:
cs4p1=4
cs4p2=3
cs4p3=4
cs4p4=4
cs4p5=3
cs4p6=2
cs4p7=2
cs4p8=2
cs4p9=1

#scores:
s4p1=int(input("Enter Score for Sub 1: "))
s4p2=int(input("Enter Score for Sub 2: "))
s4p3=int(input("Enter Score for Sub 3: "))
s4p4=int(input("Enter Score for Sub 4: "))
s4p5=int(input("Enter Score for Sub 5: "))
s4p6=int(input("Enter Score for Lab 1: "))
s4p7=int(input("Enter Score for Lab 2: "))
s4p8=int(input("Enter Score for Lab 3: "))
s4p9=int(input("Enter Score for Lab 4: "))

#credit*score
css4p1=s4p1*cs4p1
css4p2=s4p2*cs4p2
css4p3=s4p3*cs4p3
css4p4=s4p4*cs4p4
css4p5=s4p5*cs4p5
css4p6=s4p6*cs4p6
css4p7=s4p7*cs4p7
css4p8=s4p8*cs4p8
css4p9=s4p9*cs4p9

print("\n",css4p1,css4p2,css4p3,css4p4,css4p5,css4p6,css4p7,css4p8,css4p9,"\n")

s4ssum=css4p1+css4p2+css4p3+css4p4+css4p5+css4p6+css4p7+css4p8+css4p9
s4csum=cs4p1+cs4p2+cs4p3+cs4p4+cs4p5+cs4p6+cs4p7+cs4p8+cs4p9
gpas4=s4ssum/s4csum

print(f"Score Sum is {s4ssum}\n")

print(f"Credit Sum is {s4csum}\n")

print(f"GPA for Sem 4 is {gpas4}\n")
