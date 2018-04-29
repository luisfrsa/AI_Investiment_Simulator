declare -a PARAM_MIN_TO_BUY=("0.01" "0.03" "0.05" "0.1" "0.2")
declare -a PARAM_MIN_TO_SELL=("0.005" "0.01" "0.025" "0.05" "0.1")
declare -a PARAM_DISCARD_DIV=("1.0" "1.01" "1.05" "1.1" "1.2" "1.5" "2" "3")
declare -a PARAM_DAYS_TO_DISCARD=("10" "20" "45" "60" "90")
declare -a PARAM_MIN_DAYS_TO_BEGIN=("0")
declare -a PARAM_MAX_MONEY_TO_INVEST=("1" "1.1" "1.5" "2" "2.5")
declare -a MONEY=("100000")

#declare -a PARAM_MIN_TO_BUY=("0.01" "0.05" "0.1")
#declare -a PARAM_MIN_TO_SELL=("0.005" "0.01" "0.1")
#declare -a PARAM_DISCARD_DIV=("1.0" "1.1")
#declare -a PARAM_DAYS_TO_DISCARD=("10" "20" "45" "60")
#declare -a PARAM_MIN_DAYS_TO_BEGIN=("0")
#declare -a PARAM_MAX_MONEY_TO_INVEST=("1" "1.1")
#declare -a MONEY=("100000")



#declare -a PARAM_MIN_TO_BUY=("5" "50")
#declare -a PARAM_MIN_TO_SELL=("3" "30")
#declare -a PARAM_DISCARD_DIV=("4" "4")
#declare -a PARAM_DAYS_TO_DISCARD=("1" "10")
#declare -a PARAM_MIN_DAYS_TO_BEGIN=("0")
#declare -a PARAM_MAX_MONEY_TO_INVEST=("6" "60")
#declare -a MONEY=("100000")


javac src/*.java -d build/
cd build/
#java AIInvestimentSimulator 0.01 0.01 1.1 15 0 1 100000 0

for PARAM_MIN_TO_BUY_VAR in "${PARAM_MIN_TO_BUY[@]}";
do
    for PARAM_MIN_TO_SELL_VAR in "${PARAM_MIN_TO_SELL[@]}";
    do
        for PARAM_DISCARD_DIV_VAR in "${PARAM_DISCARD_DIV[@]}";
        do
            for PARAM_DAYS_TO_DISCARD_VAR in "${PARAM_DAYS_TO_DISCARD[@]}";
            do
                for PARAM_MIN_DAYS_TO_BEGIN_VAR in "${PARAM_MIN_DAYS_TO_BEGIN[@]}";
                do
                    for PARAM_MAX_MONEY_TO_INVEST_VAR in "${PARAM_MAX_MONEY_TO_INVEST[@]}";
                    do
                        for MONEY_VAR in "${MONEY[@]}";
                        do
                            java AIInvestimentSimulator $PARAM_MIN_TO_BUY_VAR $PARAM_MIN_TO_SELL_VAR $PARAM_DISCARD_DIV_VAR $PARAM_DAYS_TO_DISCARD_VAR $PARAM_MIN_DAYS_TO_BEGIN_VAR $PARAM_MAX_MONEY_TO_INVEST_VAR $MONEY_VAR  0
                            #echo "java AIInvestimentSimulator $PARAM_MIN_TO_BUY_VAR $PARAM_MIN_TO_SELL_VAR $PARAM_DISCARD_DIV_VAR $PARAM_DAYS_TO_DISCARD_VAR $PARAM_MIN_DAYS_TO_BEGIN_VAR $PARAM_MAX_MONEY_TO_INVEST_VAR $MONEY_VAR 0"
                        done
                    done
                done
            done
        done
    done
done


cd ..