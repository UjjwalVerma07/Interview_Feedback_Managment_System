import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder,OneHotEncoder
import pickle
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from sklearn.metrics import accuracy_score,f1_score,classification_report
from sqlalchemy import create_engine
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier



DATABASE_URI='postgresql://postgres:49812884@localhost:5432/ifms_database'

engine=create_engine(DATABASE_URI)

skill_rating_df=pd.read_sql('SELECT * FROM skill_ratings',engine)
feedback_df=pd.read_sql('SELECT * FROM feedback',engine)
print("Skill Rating Table:")
print(skill_rating_df.head())

print("\nFeedback Table:")
print(feedback_df.head())

# Assume these are your two dataframes

# Merge them
merged_df = skill_rating_df.merge(feedback_df, left_on="feedback_id", right_on="id", how="left")
print(merged_df['comments_x']);

df=merged_df.copy();
print(df.head());


# 2. Pivot the data
pivot_df = df.pivot_table(
    index=['interview_id'], 
    columns='skill_name', 
    values='rating', 
    aggfunc='first'
).reset_index()

# 3. Merge with decision (if needed)
decision_df = df[['interview_id', 'decision']].drop_duplicates()

final_df = pivot_df.merge(decision_df, on='interview_id', how='left')

# 4. Optional: Rename skill columns
final_df = final_df.rename(columns=lambda x: x + "_rating" if x not in ['interview_id', 'decision'] else x)

# 5. Final result
print(final_df)
df=final_df.copy();
print(df.columns)

rating_mapping = {
    "Poor": 0,
    "Average": 1,
    "Good": 2,
    "Very Good": 3,
    "Not Evaluated": 4
}

# List of all skill rating columns
rating_columns = [
    'Basic Algorithm_rating', 'Code and Syntax_rating', 'Communication_rating',
    'Design Patterns_rating', 'Git_rating', 'Learning Ability_rating',
    'Overall Attitude_rating', 'Resume Explanation_rating', 'SQL_rating'
]

# Apply the rating mapping to all rating columns
for col in rating_columns:
    df[col] = df[col].map(rating_mapping)

print(df.head())

decision_mapping={
    "REJECTED":1,
    "L1_PASSED_WITH_COMMENT":2,
    "L1_PASSED":3
}
df['decision_encoded'] = df['decision'].map(decision_mapping)
print(df.head())

print(df.columns)



df.drop(['decision'],axis=1,inplace=True)

print(df.columns)
# Define features (X) and target (y)
X = df.drop(columns=['interview_id', 'decision_encoded'])
y = df['decision_encoded']
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)



# #This is Logistic Regression;
lr=LogisticRegression();
lr.fit(X,y);
# print(lr.classes_)
# # y_pred_lr=lr.predict(X_test);
# # print(f"Logistic Regression Accuracy:",accuracy_score(y_pred_lr,y_test));



#This is Logistic Regression;
# lr=LogisticRegression();
# lr.fit(X_train,y_train);
# y_pred_lr=lr.predict(X_test);
# print(f"Logistic Regression Accuracy:",accuracy_score(y_pred_lr,y_test));

#This is Decision Tree:
# dt=DecisionTreeClassifier();
# dt.fit(X_train,y_train)
# y_pred_dt=dt.predict(X_test);
# print(f"Decision Tree Accuracy:",accuracy_score(y_pred_dt,y_test));

# #This is Random Forest;
# rf=RandomForestClassifier()
# rf.fit(X_train,y_train)
# y_pred_rf=rf.predict(X_test);
# print("Random Forest Accuracy:",accuracy_score(y_pred_rf,y_test));

# #This is Support Vector Machine;
# svm=SVC();
# svm.fit(X_train,y_train)
# y_pred_svm=svm.predict(X_test);
# print("Support Vector Machine Accuracy: ",accuracy_score(y_pred_svm,y_test));

# #This is KNN
# knn=KNeighborsClassifier();
# knn.fit(X_train,y_train);
# y_pred_knn=knn.predict(X_test);
# print("KNN Accuracy: ",accuracy_score(y_test,y_pred_knn));


best_model=lr
# best_score=accuracy_score(lr.predict(X_test),y_test);
        
#Save the best model:-

with open('best_model.pkl','wb') as f:
    pickle.dump(best_model,f)

