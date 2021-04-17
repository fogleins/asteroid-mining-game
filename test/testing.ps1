$testname=$args[0]
# $outputfile=$testname+"_output.txt"
$outputfile="output.txt"
$testcasefile=$testname+"_expected_output.txt"

$result=compare-object (Get-Content -Path $outputfile) (Get-Content -Path $testcasefile)

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
