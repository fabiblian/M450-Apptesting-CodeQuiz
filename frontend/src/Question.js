import React from "react";
import Button from "./Button.js";

class Question extends React.Component {
  render() {
    const buttons = this.props.answers.map((answer) => (
      <Button
        label={answer.answer}
        key={answer.id}
        onClick={() => this.props.callback(answer)}
      />
    ));

    return (
      <div className="question">
        <h2>{this.props.question}</h2>
        <img src="img/question_smiley.png" alt="" width="150px" />
        <hr />
        <div className="button-bar">
          {buttons}
        </div>
      </div>
    );
  }
}

export default Question;