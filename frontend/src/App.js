import './App.css';
import React from "react";
import Table from "./components/Table";

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      loaded: false
    };
  }

  formatRows() {
    let buy_rows = [];
    let sell_rows = [];
    let rec_rows = [];
    let exchanges = [];
    let first_coin = true;
    for (const coin of this.state.coins) {
      let b_row = [coin.ticker];
      let s_row = [coin.ticker];
      let r_row = [coin.ticker];
      for (const [key,value] of Object.entries(coin.buy_prices)) {
        b_row.push(value);
        if (first_coin) {
          exchanges.push(key);
        }
      }
      first_coin = false;
      for (const [key,value] of Object.entries(coin.sell_prices)) {
        s_row.push(value);
      }
      r_row.push(coin.buy_rec);
      r_row.push(coin.sell_rec);

      buy_rows.push(b_row);
      sell_rows.push(s_row);
      rec_rows.push(r_row);
    }
    return [exchanges, buy_rows, sell_rows, rec_rows];
  }

  componentDidMount() {
    fetch("http://localhost:8080/coinexchange")
    .then(res => res.json())
    .then(
        (res) => {
          this.setState({
            coins: res,
            loaded: true
          });
        }
    );
  }

  render() {
    console.log(this.state);
    let exchanges = [];
    let buy_rows = []
    let sell_rows = []
    let rec_rows = []
    const rec_headers = ["Buy", "Sell"]
    if (this.state.loaded) {
      [exchanges, buy_rows, sell_rows, rec_rows] = this.formatRows();
      return (
        <div>
          <Table title="Buy Prices" headers={exchanges} rows={buy_rows} />
          <Table title="Sell Prices" headers={exchanges} rows={sell_rows} />
          <Table title="Recommendations" headers={rec_headers} rows={rec_rows} />
        </div>
      );
    } else {
      return("Loading...")
    }
  }
}


export default App;
