Feature: Recording of affective measures

  Background:
    Given there is an OpenAffect server

  Scenario: report a measure
    Given I have an affective payload
    When I POST it to the /measures endpoint
    Then I receive a 201 status code

  Scenario: get list of measures
    When I do a GET on the /measures endpoint
    Then I receive a 200 status code
    And the payload is a non-empty list
