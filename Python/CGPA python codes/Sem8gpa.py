#sem8:
print("")
print("Input must be in numbers")
print("O  -- 10")
print("A+ -- 09")
print("A  -- 08")
print("B+ -- 07")
print("B  -- 06")
print("")

print("Sem 8:")
#credits:
cs8p1=3
cs8p2=3
cs8p3=3
cs8p4=10
print("")

#scores:
s8p1=int(input("Enter Score for Sub 1           : "))
s8p2=int(input("Enter Score for Sub 2           : "))
s8p3=int(input("Enter Score for Sub 3           : "))
s8p4=int(input("Enter Score for Sub 4           : "))
print("")

#credit*score
css8p1=s8p1*cs8p1
css8p2=s8p2*cs8p2
css8p3=s8p3*cs8p3
css8p4=s8p4*cs8p4
print("")

print(css8p1,css8p2,css8p3,css8p4)
print("")
s8ssum=css8p1+css8p2+css8p3+css8p4
s8csum=cs8p1+cs8p2+cs8p3+cs8p4
gpas8=s8ssum/s8csum
print(f"Score Sum is {s8ssum}")
print("")
print(f"Credit Sum is {s8csum}")
print("")
print(f"GPA for Sem 5 is {gpas8}")
print("")