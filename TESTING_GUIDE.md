# Testing Guide for DailyArt App

## Manual Testing Steps

### 1. **Name Entry Screen (NameActivity)**
- ✅ Open the app - should show name entry screen first
- ✅ Try entering a name and clicking "Enter"
- ✅ Try leaving it empty - should show error message "Please enter your name"
- ✅ After entering name, should navigate to MainActivity with welcome toast

### 2. **Main Paintings List (MainActivity)**
- ✅ Verify all 7 paintings are displayed:
  - Mona Lisa - Leonardo da Vinci
  - The Birth of Venus - Sandro Botticelli
  - The Scream - Evard Munch
  - American Gothic - Grant Wood
  - The Arnolfini - Jan van Eyck
  - Whistler's Mother - James McNeill Whistler
  - Girl with a Pearl - Johannes Vermeer
- ✅ Verify each painting shows:
  - Painting image
  - Painting name (bold, larger text)
  - Author name (smaller text)
  - Heart icon button (should be outline/sharp initially)

### 3. **Favorites Feature**
- ✅ Click heart icon on a painting - should change to filled heart
- ✅ Click filled heart - should change back to outline
- ✅ Add multiple paintings to favorites
- ✅ Open favorites menu (top right) - should show all favorited paintings
- ✅ Remove favorite from favorites screen - should update correctly
- ✅ Go back to main list - favorites should persist (filled hearts remain)

### 4. **Favorites Fragment**
- ✅ Click menu icon (top right) to open favorites
- ✅ Verify only favorited paintings are shown
- ✅ Click "Back to Paintings" button - should return to main list
- ✅ Remove favorite from favorites list - should disappear
- ✅ Add new favorite from main list, then open favorites - new one should appear

### 5. **Bug Fixes Verification**
- ✅ Heart icons display correctly (no missing drawable errors)
- ✅ Favorites persist when scrolling list
- ✅ Adding/removing favorites works correctly
- ✅ No crashes when toggling favorites rapidly

## Automated Testing

### Run Unit Tests (Local JVM)
```powershell
.\gradlew.bat test
```

### Run Instrumented Tests (On Emulator/Device)
```powershell
.\gradlew.bat connectedAndroidTest
```

### Run All Tests
```powershell
.\gradlew.bat test connectedAndroidTest
```

### View Test Results
After running tests, results are in:
- Unit tests: `app/build/test-results/`
- Instrumented tests: `app/build/outputs/androidTest-results/`

## Quick Test Commands

### Rebuild and Install
```powershell
.\gradlew.bat assembleDebug
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

### Launch App
```powershell
adb shell am start -n com.example.testproject.f120168/com.example.f120268.NameActivity
```

### Clear App Data (Fresh Start)
```powershell
adb shell pm clear com.example.testproject.f120168
```

### View Logs
```powershell
adb logcat | findstr "DailyArt\|f120268"
```

## Test Checklist

- [ ] App launches without crashes
- [ ] Name validation works
- [ ] All paintings display correctly
- [ ] Heart icons show (sharp and filled)
- [ ] Add to favorites works
- [ ] Remove from favorites works
- [ ] Favorites menu shows correct paintings
- [ ] Favorites persist after app restart
- [ ] Navigation between screens works
- [ ] No memory leaks (test by adding/removing many favorites)

