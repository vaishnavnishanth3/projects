#sem5:

print("Input must be in numbers")
print("O  -- 10")
print("A+ -- 09")
print("A  -- 08")
print("B+ -- 07")
print("B  -- 06\n")


print("Sem 5:\n")
#credits:
cs5p1=3
cs5p2=3
cs5p3=3
cs5p4=3
cs5p5=3
cs5p6=3
cs5p7=2
cs5p8=2
cs5p9=2

#scores:
s5p1=int(input("Enter Score for Sub 1           : "))
s5p2=int(input("Enter Score for Sub 2           : "))
s5p3=int(input("Enter Score for Sub 3           : "))
s5p4=int(input("Enter Score for Sub 4           : "))
s5p5=int(input("Enter Score for Sub 5           : "))
s5p6=int(input("Enter Score for Open Elective I : "))
s5p7=int(input("Enter Score for Lab 1           : "))
s5p8=int(input("Enter Score for Lab 2           : "))
s5p9=int(input("Enter Score for Naan Mudhalvam  : "))

#credit*score
css5p1=s5p1*cs5p1
css5p2=s5p2*cs5p2
css5p3=s5p3*cs5p3
css5p4=s5p4*cs5p4
css5p5=s5p5*cs5p5
css5p6=s5p6*cs5p6
css5p7=s5p7*cs5p7
css5p8=s5p8*cs5p8

print(css5p1,css5p2,css5p3,css5p4,css5p5,css5p6,css5p7,css5p8,"\n")

s5ssum=css5p1+css5p2+css5p3+css5p4+css5p5+css5p6+css5p7+css5p8
s5csum=cs5p1+cs5p2+cs5p3+cs5p4+cs5p5+cs5p6+cs5p7+cs5p8
gpas5=s5ssum/s5csum
print(f"Score Sum is {s5ssum}\n")

print(f"Credit Sum is {s5csum}\n")

print(f"GPA for Sem 5 is {gpas5}\n")
