#sem3:

print("Input must be in numbers")
print("O  -- 10")
print("A+ -- 09")
print("A  -- 08")
print("B+ -- 07")
print("B  -- 06\n")

print("Sem 3:")
#credits:
cs3p1=4
cs3p2=3
cs3p3=3
cs3p4=3
cs3p5=3
cs3p6=2
cs3p7=2
cs3p8=2
cs3p9=1

print(cs3p1,cs3p2,cs3p3,cs3p4,cs3p5,cs3p6,cs3p7,cs3p8,cs3p9,"\n")

#scores:
s3p1=int(input("Enter Score for Sub 1: "))
s3p2=int(input("Enter Score for Sub 2: "))
s3p3=int(input("Enter Score for Sub 3: "))
s3p4=int(input("Enter Score for Sub 4: "))
s3p5=int(input("Enter Score for Sub 5: "))
s3p6=int(input("Enter Score for Lab 1: "))
s3p7=int(input("Enter Score for Lab 2: "))
s3p8=int(input("Enter Score for Lab 3: "))
s3p9=int(input("Enter Score for Lab 4: "))
print("")

#credit*score
css3p1=s3p1*cs3p1
css3p2=s3p2*cs3p2
css3p3=s3p3*cs3p3
css3p4=s3p4*cs3p4
css3p5=s3p5*cs3p5
css3p6=s3p6*cs3p6
css3p7=s3p7*cs3p7
css3p8=s3p8*cs3p8
css3p9=s3p9*cs3p9
print("")

print(css3p1,css3p2,css3p3,css3p4,css3p5,css3p6,css3p7,css3p8,css3p9)
print("")
s3ssum=css3p1+css3p2+css3p3+css3p4+css3p5+css3p6+css3p7+css3p8+css3p9
s3csum=cs3p1+cs3p2+cs3p3+cs3p4+cs3p5+cs3p6+cs3p7+cs3p8+cs3p9
gpas3=s3ssum/s3csum
print(f"Score Sum is {s3ssum}")
print("")
print(f"Credit Sum is {s3csum}")
print("")
print(f"GPA for Sem 3 is {gpas3}")
print("")