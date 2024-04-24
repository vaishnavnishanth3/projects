#sem2:

print("Input must be in numbers")
print("O  -- 10")
print("A+ -- 09")
print("A  -- 08")
print("B+ -- 07")
print("B  -- 06\n")

print("Sem 2:\nCredits")

#credits:
cs2p1=4
cs2p2=4
cs2p3=3
cs2p4=3
cs2p5=3
cs2p6=4
cs2p7=2
cs2p8=2

print(cs2p1,cs2p2,cs2p3,cs2p4,cs2p5,cs2p6,cs2p7,cs2p8,"\n")

print("Scores:")
#scores:
s2p1=int(input("Enter Score for Sub 1: "))
s2p2=int(input("Enter Score for Sub 2: "))
s2p3=int(input("Enter Score for Sub 3: "))
s2p4=int(input("Enter Score for Sub 4: "))
s2p5=int(input("Enter Score for Sub 5: "))
s2p6=int(input("Enter Score for Sub 6: "))
s2p7=int(input("Enter Score for Lab 1: "))
s2p8=int(input("Enter Score for Lab 2: "))
print("")

print("Credit*Scores:")
#creditS*score
css2p1=s2p1*cs2p1
css2p2=s2p2*cs2p2
css2p3=s2p3*cs2p3
css2p4=s2p4*cs2p4
css2p5=s2p5*cs2p5
css2p6=s2p6*cs2p6
css2p7=s2p7*cs2p7
css2p8=s2p8*cs2p8
print("")

print(css2p1,css2p2,css2p3,css2p4,css2p5,css2p6,css2p7,css2p8)
print("")
s2ssum=css2p1+css2p2+css2p3+css2p4+css2p5+css2p6+css2p7+css2p8
s2csum=cs2p1+cs2p2+cs2p3+cs2p4+cs2p5+cs2p6+cs2p7+cs2p8
gpas2=s2ssum/s2csum
print(f"Score Sum is {s2ssum}")
print("")
print(f"Credit Sum is {s2csum}")
print("")
print(f"GPA for Sem 2 is {gpas2}")
print("")