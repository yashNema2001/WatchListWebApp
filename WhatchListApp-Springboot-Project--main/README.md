# WhatchListApp-Springboot-Project-

# Overview
WatchlistApp is a Spring Boot application designed to help users manage a list of movies they want to watch. Users can add movies to their watchlist along with ratings, priority levels, and personal comments. The app automatically fetches movie ratings from an external API, ensuring that the watchlist is always up to date with the latest information.

# Features
1. Add Movies to Watchlist: Users can add movies by entering the movie name, setting a priority level, and adding personal comments.
2. Automatic Rating Fetching: The app fetches the latest ratings for each movie from an external movie database API.
3. View and Update Watchlist: Users can view their entire watchlist, update movie details, or remove movies they have watched.
4. Priority Management: Users can prioritize movies, helping them decide which movies to watch first.
5. Comment Section: Users can add and update personal comments for each movie.

# Technologies Used
1. Java & Spring Boot: Core framework for developing the backend of the application.
2. Thymeleaf: For rendering dynamic HTML views.
3. MySQL Database: A database used for storing watchlist data.
4. RESTful API: Integration with an external movie database API for fetching movie ratings.
5. Maven: For project build and dependency management.

# Usage
1. Home Page: Displays your watchlist with all added movies, including their ratings, priority, and comments.
2. Add Movie: Use the "Add Movie" button to add a new movie to your watchlist.
3. Update Movie: Click on a movie to update its details or remove it from the watchlist.
4. Rating Fetch: Ratings are automatically fetched and updated when a movie is added.
