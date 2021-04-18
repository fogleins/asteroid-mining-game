$scriptPath = Get-Location
$files = Get-ChildItem -Path $scriptPath\test\

foreach ($f in $files) {
    $name = $f.Name
    if (! $name.EndsWith("_out.txt")) {
        $testName = $name.Replace(".txt","")
        Write-Host `n "Testing: " $testName
        .\test.ps1 $testName
    }
}