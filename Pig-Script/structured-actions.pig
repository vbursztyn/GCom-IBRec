raw = LOAD 'actions.log' USING PigStorage(',') AS (user, url);
grouped = GROUP raw BY (user, url);
counted = FOREACH grouped GENERATE group, COUNT($1);
result = FOREACH counted GENERATE flatten($0), $1;
STORE result INTO 'structured-actions.csv' USING PigStorage(',');

