from flask import Flask
from threading import Thread

# --------- EcoLog Wallet App ----------
ecolog_app = Flask("EcoLog")

@ecolog_app.route("/")
def ecolog_index():
    return "<h2>EcoLog Wallet</h2><p>✅ EcoLog is running on port 5000</p>"


# --------- MGAO API ----------
mgao_app = Flask("MGAO")

@mgao_app.route("/")
def mgao_index():
    return "<h2>MGAO API</h2><p>✅ MGAO API is running on port 5001</p>"


# --------- Server Runner ----------
def run_app(app, port):
    app.run(host="127.0.0.1", port=port)


def start_server():
    # Run EcoLog on 5000
    thread1 = Thread(target=run_app, args=(ecolog_app, 5000))
    thread1.daemon = True
    thread1.start()

    # Run MGAO API on 5001
    thread2 = Thread(target=run_app, args=(mgao_app, 5001))
    thread2.daemon = True
    thread2.start()

    print("✅ EcoLog started at http://127.0.0.1:5000")
    print("✅ MGAO API started at http://127.0.0.1:5001")