from pymongo import MongoClient
import string
import random
try:
    conn = MongoClient()
    print("Connected successfully!!!")
except:
    print("Could not connect to MongoDB")
# database
db = conn.seriesInfo
# Created or Switched to collection names: Series
collection = db.series
genreArray = ["Dark", "Drama", "Thriller", "Suspense", "Mind Bending", "Biography"]
tvSeriesArray = ["True Detective", "Heroes", "AOS", "Iron Fist", "The Sinner", "13 Reason Why", "Riverdale", "Sherlock",
                    "Dark", "Hannibal", "The Mentalist", "House M.D.", "Criminal Mind", "Person Of Interest", "The Stranger",
                    "Chernobyl", "The Flash", "The Arrow", "Legned's Of Tomorrow", "GOT", "Stranger Things", "Lucifer"
                    , "The End of the ** World", "The Boys", "Silicon Valley", "Mr. Robot", "Titans", "Sacred Games", "Black Mirror"
                    , "DareDevil", "Lock and Key", "Ragnarok", "BroadChurch",  "Mindhunter", "The Witcher", "I am not Okay with this", "The 100"
                    "You", "The Hauntin Of Hill House", "Asur", "Peaky Blinders", "Genius", "Fargo", "The Order", "Ozark",
                    "Salavation", "Rick and Morty", "Planet Earth", "Money Heist", "Suits"]

descriptionArray = ["Based on Time Travel", "Based on true Story", "Based on Psychology", "Based on Drugs", "Based on Finance", "Based on Sci-Fi"
                        ,"Based on Criminal", "Based on Magic", "Based on dark crimes"]

for i in range(50000):
    id = str(i)
    name = random.choice(tvSeriesArray)
    genre = random.choice(genreArray)
    description = random.choice(descriptionArray)
    rating = round(random.uniform(0, 5), 2)
    numberOfSeason = random.randint(1, 15)

    series = {
        "_id":id,
        "name": name,
        "genre" : genre,
        "description": description,
        "rating":rating,
        "numberOfSeason":numberOfSeason
            }
    rec = collection.insert_one(series)
    print("Data inserted with record id : ",rec , "\n")

print("\n\n\nInsert done Success!!\n\n")