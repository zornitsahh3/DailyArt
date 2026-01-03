# Metropolitan Museum of Art API Integration

## Overview
The DailyArt app now integrates with the Metropolitan Museum of Art API to fetch real paintings dynamically. The app loads local paintings first (for offline support) and then fetches additional paintings from the API.

## What Was Added

### 1. Dependencies
- **Retrofit 2.9.0** - HTTP client for API calls
- **Gson 2.9.0** - JSON parsing
- **OkHttp 4.12.0** - HTTP client library
- **Glide 4.16.0** - Image loading from URLs

### 2. API Classes
- `MetMuseumService.java` - Retrofit interface for API endpoints
- `ApiClient.java` - Retrofit client setup
- `MetMuseumResponse.java` - Response model for object IDs
- `Artwork.java` - Model for artwork data
- `PaintingApiHelper.java` - Helper class to fetch random paintings

### 3. Database Updates
- Added `imageUrl` field to `Painting` entity
- Database version updated to 2
- Migration added for existing databases

### 4. Code Updates
- **Painting.java**: Added support for image URLs alongside local drawables
- **MyCustomAdapter.java**: Uses Glide to load images from URLs
- **MainActivity.java**: Fetches 10 random paintings from API on startup
- **AndroidManifest.xml**: Added internet permission

## How It Works

1. **On App Start**: 
   - Loads local paintings from database (7 default paintings)
   - Displays them immediately
   - Fetches 10 random paintings from Metropolitan Museum API in background

2. **API Fetching**:
   - Queries European Paintings department (Department ID: 11)
   - Gets random object IDs
   - Fetches artwork details for each
   - Only includes paintings with images

3. **Image Loading**:
   - Local paintings: Uses drawable resources
   - API paintings: Uses Glide to load from URLs
   - Images are cached for offline viewing

4. **Data Storage**:
   - API paintings are saved to database
   - Duplicate checking prevents adding same painting twice
   - Favorites work with both local and API paintings

## API Endpoints Used

- `GET /public/collection/v1/search?departmentId=11&hasImages=true` - Get paintings with images
- `GET /public/collection/v1/objects/{objectID}` - Get specific artwork details

## Features

✅ **Offline Support**: Local paintings always available
✅ **Dynamic Content**: Fetches new paintings from API
✅ **Image Caching**: Glide caches images for offline viewing
✅ **Error Handling**: Shows user-friendly error messages
✅ **Duplicate Prevention**: Won't add same painting twice
✅ **Seamless Integration**: Works with existing favorites feature

## Testing

To test the API integration:
1. Run the app with internet connection
2. Wait for "Loading paintings from Metropolitan Museum..." message
3. New paintings should appear in the list
4. Images should load from URLs
5. Test offline mode - local paintings should still work

## Future Enhancements

- Add pull-to-refresh to fetch more paintings
- Add search functionality
- Filter by artist or period
- Show painting details screen
- Add pagination for loading more paintings

