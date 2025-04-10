import pickle
def predict_decision_from_ratings(skill_ratings: dict):
    """
    skill_ratings: dict
        Example:
        {
            "Basic Algorithm": "Good",
            "Code and Syntax": "Very Good",
            "Communication": "Average",
            ...
        }
    """

    # 1. Map the string ratings to numerical values
    rating_mapping = {
        "Poor": 0,
        "Average": 1,
        "Good": 2,
        "Very Good": 3,
        "Not Evaluated": 4
    }

    # 2. List of expected skills (must match model training)
    expected_skills = [
        'Basic Algorithm', 'Code and Syntax', 'Communication',
        'Design Patterns', 'Git', 'Learning Ability',
        'Overall Attitude', 'Resume Explanation', 'SQL'
    ]

    # 3. Prepare input in correct order
    input_data = []
    for skill in expected_skills:
        rating = skill_ratings.get(skill, "Not Evaluated")  # Default if missing
        input_data.append(rating_mapping.get(rating, 4))    # Default to "Not Evaluated" = 4

    # 4. Load the trained model
    with open('best_model.pkl', 'rb') as f:
        model = pickle.load(f)

    # 5. Predict
    prediction = model.predict([input_data])[0]  # Single prediction

    # 6. Decode prediction back to label
    decision_reverse_mapping = {
        1: "REJECTED",
        2: "L1_PASSED_WITH_COMMENT",
        3: "L1_PASSED"
    }

    return decision_reverse_mapping.get(prediction, "Unknown")


if __name__ == "__main__":
    # Example input from frontend:
    frontend_input = {
        "Basic Algorithm": "Good",
        "Code and Syntax": "Very Good",
        "Communication": "Average",
        "Design Patterns": "Good",
        "Git": "Average",
        "Learning Ability": "Very Good",
        "Overall Attitude": "Good",
        "Resume Explanation": "Average",
        "SQL": "Good"
    }

    decision = predict_decision_from_ratings(frontend_input)
    print("Predicted Decision:", decision)

