
if [ $POSTGRES_DB = "test" ] 
then 
    echo "Running tests"
    python3 -m unittest *.py
else
    echo "Database $POSTGRES_DB other than test. Skipping tests"
fi
