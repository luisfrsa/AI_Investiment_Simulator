#declare -a PARAM_MIN_TO_BUY=("0.01" "0.03" "0.05" "0.1")
#declare -a PARAM_MIN_TO_SELL=("0.05" "0.1" "0.15" "0.2" "0.25")
#declare -a PARAM_DISCARD_DIV=("2" "2.5" "3.5")
#declare -a PARAM_DAYS_TO_DISCARD=("2" "5" "10")
#declare -a PARAM_MIN_DAYS_TO_BEGIN=("0")
#declare -a PARAM_MAX_MONEY_TO_INVEST=("1" "1.1")
#declare -a MONEY=("100000")
#declare -a INSTANCES=("2011-2012,2013" "2012-2013,2014" "2013-2014,2015" "2014-2015,2016" "2015-2016,2017")
#declare -a INSTANCES=("2010-2011,2012")

declare -a PARAM_MIN_TO_BUY=("0.03")
declare -a PARAM_MIN_TO_SELL=("0.15")
declare -a PARAM_DISCARD_DIV=("2.5")
declare -a PARAM_DAYS_TO_DISCARD=("5")
declare -a PARAM_MIN_DAYS_TO_BEGIN=("0")
declare -a PARAM_MAX_MONEY_TO_INVEST=("1")
declare -a MONEY=("100000")
#declare -a INSTANCES=("2011-2012,2013-2014-2015-2016-2017" "2012-2013,2014-2015-2016-2017" "2013-2014,2015-2016-2017" "2014-2015,2016-2017" "2015-2016,2017")
#declare -a INSTANCES=("2010-2011,2012")
#declare -a INSTANCES=("2012-2013,2014")
declare -a INSTANCES=(",2013" ",2014" ",2015" ",2016" ",2017")



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