declare -a PARAM_MIN_TO_BUY=("0.03")
declare -a PARAM_MIN_TO_SELL=("0.15")
declare -a PARAM_DISCARD_DIV=("2.5")
declare -a PARAM_DAYS_TO_DISCARD=("5")
declare -a PARAM_MIN_DAYS_TO_BEGIN=("0")
declare -a PARAM_MAX_MONEY_TO_INVEST=("1")
declare -a MONEY=("100000")

declare -a INSTANCES=(",2013" ",2014" ",2015" ",2016" ",2017")

javac src/*.java -d build/
cd build/

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
                           for INSTANCE_VAR in "${INSTANCES[@]}";
                           do
                                java AIInvestimentSimulator $PARAM_MIN_TO_BUY_VAR $PARAM_MIN_TO_SELL_VAR $PARAM_DISCARD_DIV_VAR $PARAM_DAYS_TO_DISCARD_VAR $PARAM_MIN_DAYS_TO_BEGIN_VAR $PARAM_MAX_MONEY_TO_INVEST_VAR $MONEY_VAR  0 $INSTANCE_VAR
                                #echo "java AIInvestimentSimulator $PARAM_MIN_TO_BUY_VAR $PARAM_MIN_TO_SELL_VAR $PARAM_DISCARD_DIV_VAR $PARAM_DAYS_TO_DISCARD_VAR $PARAM_MIN_DAYS_TO_BEGIN_VAR $PARAM_MAX_MONEY_TO_INVEST_VAR $MONEY_VAR  0 $INSTANCE_VAR"
                            done
                        done
                    done
                done
            done
        done
    done
done


cd ..