# Setting up GitHub Repository for DailyArt

## Prerequisites
1. Install Git for Windows from: https://git-scm.com/download/win
2. Create a GitHub account at: https://github.com (if you don't have one)

## Quick Setup (Using the Script)

1. Open PowerShell in the project directory
2. Run the setup script:
   ```powershell
   .\setup-github.ps1
   ```
3. Follow the instructions shown by the script

## Manual Setup

If you prefer to set up manually, follow these steps:

### 1. Initialize Git Repository
```bash
git init
```

### 2. Add All Files
```bash
git add .
```

### 3. Create Initial Commit
```bash
git commit -m "Initial commit: DailyArt Android app with bug fixes"
```

### 4. Create GitHub Repository
- Go to https://github.com/new
- Repository name: `DailyArt`
- Description: "Daily Art Android app - A catalog of famous paintings with favorites feature"
- Choose Public or Private
- **DO NOT** initialize with README, .gitignore, or license (we already have these)
- Click "Create repository"

### 5. Connect Local Repository to GitHub
```bash
git remote add origin https://github.com/YOUR_USERNAME/DailyArt.git
git branch -M main
git push -u origin main
```

Replace `YOUR_USERNAME` with your actual GitHub username.

## What's Included in This Repository

- Complete Android app source code
- Bug fixes:
  - Added missing heart icons (sharp.xml and filled.xml)
  - Fixed equals() and hashCode() methods in Painting class
  - Added null safety checks
- All drawable resources
- Gradle build configuration
- Proper .gitignore for Android projects

## Future Updates

To push future changes:
```bash
git add .
git commit -m "Description of your changes"
git push
```

