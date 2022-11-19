import re
import requests

f = open("points.osm", "r")
i = 0
all = 64754686
#all = 10000
print(f"[{' '*100}]", end="")
session = requests.Session()
while f.readable():
    line = f.readline()
    if "node" in line and "id" in line and "lat" in line and "lon" in line:
        id = re.findall(r'id="\d+"', line)[0].split('"')[1]
        lat = re.findall(r'lat="\d+.\d+"', line)[0].split('"')[1]
        lon = re.findall(r'lon="\d+.\d+"', line)[0].split('"')[1]

        session.post(
            f"http://localhost:8080/points/add?id={id}&lon={lon}&lat={lat}"
        )

    if i % (all // 100) == 0:
        print(f"\r[{'*'*(i//(all//100))}{' '*(100 - i//(all//100))}]", end="")
    i += 1
print(f"[{'*'*100}]")
