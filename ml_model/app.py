from flask import Flask,request ,jsonify
import pickle
import numpy as np
from model_pipeline import predict_decision_from_ratings
app=Flask(__name__)

@app.route('/predict',methods=['POST'])
def predict():
    skill_ratings=request.get_json()
    decision=predict_decision_from_ratings(skill_ratings)
    return jsonify({'decision':decision})

if __name__=='main':
    app.run(port=5000)
    
