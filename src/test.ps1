$testname = $args[0]
$outputfile = "test\"+$testname+"_out.txt"
$testcasefile = "test\"+$testname+".txt"

Write-Host $args[1]

if ($args[1] -eq "-compile") {
    javac -cp .\ control\main.java
}

$output = Get-Content $testcasefile | java -cp .\ control.Main

$result = Compare-Object ($output) (Get-Content -Path $outputfile) -SyncWindow 0

if(($result -like "*=>*") -or ($result -like "*<=*")) {
    Write-Host "Test failed. Differences:"
    ForEach($element in $result) {
        if ($element.SideIndicator.Equals("=>")) {
                Write-Host `t "[expected, but missing in output]" `t $element.InputObject
        } elseif ($element.SideIndicator.Equals("<=")) {
                Write-Host `t "[got in output, but not expected]" `t $element.InputObject
        }
    }
} else {
    Write-Host "Test successful."
}
