# import pickle
# import numpy as np

# # Load the best model
# with open('best_model.pkl', 'rb') as f:
#     model = pickle.load(f)

# def predict_decision(features):
#     """
#     features: List or np.array of 9 skill ratings (after mapping to integers)
#     returns: predicted decision label (1, 2, or 3)
#     """
#     features = np.array(features).reshape(1, -1)
#     prediction = model.predict(features)
#     return prediction[0]

# # Example usage
# if __name__ == "__main__":
#     # Dummy test: 9 ratings [Basic Algo, Code, Communication, Design, Git, Learning, Attitude, Resume, SQL]
#     sample_features = [2, 2, 3, 1, 2, 2, 3, 2, 1]  # These should be mapped integers
#     prediction = predict_decision(sample_features)
#     print(f"Predicted Decision Encoded Label: {prediction}")
