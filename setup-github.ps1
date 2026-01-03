# PowerShell script to set up Git repository and push to GitHub
# Make sure Git is installed before running this script

Write-Host "Setting up Git repository for DailyArt project..." -ForegroundColor Green

# Check if git is installed
try {
    $gitVersion = git --version
    Write-Host "Git found: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host "ERROR: Git is not installed or not in PATH." -ForegroundColor Red
    Write-Host "Please install Git for Windows from: https://git-scm.com/download/win" -ForegroundColor Yellow
    Write-Host "After installing Git, restart your terminal and run this script again." -ForegroundColor Yellow
    exit 1
}

# Initialize git repository if not already initialized
if (-not (Test-Path .git)) {
    Write-Host "Initializing Git repository..." -ForegroundColor Cyan
    git init
} else {
    Write-Host "Git repository already initialized." -ForegroundColor Yellow
}

# Add all files
Write-Host "Adding all files to Git..." -ForegroundColor Cyan
git add .

# Check if there are changes to commit
$status = git status --porcelain
if ($status) {
    Write-Host "Creating initial commit..." -ForegroundColor Cyan
    git commit -m "Initial commit: DailyArt Android app with bug fixes"
    Write-Host "Commit created successfully!" -ForegroundColor Green
} else {
    Write-Host "No changes to commit." -ForegroundColor Yellow
}

Write-Host "`n=== Next Steps ===" -ForegroundColor Green
Write-Host "1. Go to https://github.com/new and create a new repository named 'DailyArt'" -ForegroundColor Cyan
Write-Host "2. DO NOT initialize it with a README, .gitignore, or license" -ForegroundColor Cyan
Write-Host "3. After creating the repository, run these commands:" -ForegroundColor Cyan
Write-Host "   git remote add origin https://github.com/YOUR_USERNAME/DailyArt.git" -ForegroundColor White
Write-Host "   git branch -M main" -ForegroundColor White
Write-Host "   git push -u origin main" -ForegroundColor White
Write-Host "`n(Replace YOUR_USERNAME with your actual GitHub username)" -ForegroundColor Yellow

