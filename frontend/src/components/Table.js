import React from "react";
import "./Table.css";

export default class Table extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      title: props.title,
      columnHeaders: [""].concat(props.headers),
      rows: props.rows
    };
  }

  formatRow(row) {
    const values = row.map((item) => <td>{item}</td>);
    return (
      <tr>{values}</tr>
    );
  }

  render() {
    const headers = this.state.columnHeaders.map((item) => <th>{item}</th>);
    const rows = this.state.rows.map((item) => this.formatRow(item));
    return (
      <div>
        <h1>{this.state.title}</h1>
        <table>
          <tr>
            { headers }
          </tr>
          { rows }
        </table>
      </div>
    );
  }
}
