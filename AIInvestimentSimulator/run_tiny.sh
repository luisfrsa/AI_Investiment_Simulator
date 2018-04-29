declare -a PARAM_MIN_TO_BUY=("0.01" "0.03" "0.05" "0.1" "0.2")
declare -a PARAM_MIN_TO_SELL=("0.005" "0.01" "0.025" "0.05" "0.1")
declare -a PARAM_DISCARD_DIV=("1.0" "1.01" "1.05" "1.1" "1.2" "1.5" "2" "3")
declare -a PARAM_DAYS_TO_DISCARD=("10" "20" "45" "60" "90")
declare -a PARAM_MIN_DAYS_TO_BEGIN=("0")
declare -a PARAM_MAX_MONEY_TO_INVEST=("1" "1.1" "1.5" "2" "2.5")
declare -a MONEY=("100000")

javac src/*.java -d build/
cd build/
java AIInvestimentSimulator
cd ..