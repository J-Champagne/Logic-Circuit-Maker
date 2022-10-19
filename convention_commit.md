
# Convention commit

<type>[optional scope]: <description>

[optional body]

[optional footer(s)]







## Type
Type | Description | usage |
|:--------|:----:|:-----:|
| fix: | corriger un bug| fix: exception for a number in range
|feat:  | ajout d'un nouvel elément dans le code| feat: class Circuit
|build:  | | build:
|chore:  | | chore:
|ci:  | | ci:
|docs:  | | docs:
|style:  | | style:
|refactor:  | | refactor:
|perf:  | | perf:
|test:  | | test:


## [optional scope]

scope | Description | usage |
|:--------|:----:|:-----:|
| parser | attention | feat (parser) !: add ability to parse arrays

## keyword

The key words
“MUST”
“MUST NOT”
“REQUIRED”
“SHALL”
“SHALL NOT”
“SHOULD”
“SHOULD NOT”
“RECOMMENDED”
“MAY”
“OPTIONAL”

## specification

- Commits MUST be prefixed with a type, which consists of a noun, feat, fix, etc.,
feat: add Base1

- followed by the OPTIONAL scope, OPTIONAL !, and REQUIRED terminal colon and space.

- feat: DOIT être utilisé quand le commit ajoute une nouvelle option dans l'applicaiton ou la library

- fix: DOIT être utilisé lorsqu'un commit représente un bug qui a été corrigé dans l'application

- le scope PEUT être placé après le type et DOIT être mis en parenthèse, il consiste en un nom qui décrits une section dans le code.

- A scope MAY be provided after a type. A scope MUST consist of a noun describing a section of the codebase surrounded by parenthesis, e.g., fix(parser):

- A description MUST immediately follow the colon and space after the type/scope prefix. The description is a short summary of the code changes, e.g., fix: array parsing issue when multiple spaces were contained in string.

- A longer commit body MAY be provided after the short description, providing additional contextual information about the code changes. The body MUST begin one blank line after the description.

- A commit body is free-form and MAY consist of any number of newline separated paragraphs.

- One or more footers MAY be provided one blank line after the body. Each footer MUST consist of a word token, followed by either a :<space> or <space># separator, followed by a string value (this is inspired by the git trailer convention).

- A footer’s token MUST use - in place of whitespace characters, e.g., Acked-by (this helps differentiate the footer section from a multi-paragraph body). An exception is made for BREAKING CHANGE, which MAY also be used as a token.

- A footer’s value MAY contain spaces and newlines, and parsing MUST terminate when the next valid footer token/separator pair is observed.

- Breaking changes MUST be indicated in the type/scope prefix of a commit, or as an entry in the footer.

- If included as a footer, a breaking change MUST consist of the uppercase text BREAKING CHANGE, followed by a colon, space, and description, e.g., BREAKING CHANGE: environment variables now take precedence over config files.

- If included in the type/scope prefix, breaking changes MUST be indicated by a ! immediately before the :. If ! is used, BREAKING CHANGE: MAY be omitted from the footer section, and the commit description SHALL be used to describe the breaking change.

- Types other than feat and fix MAY be used in your commit messages, e.g., docs: updated ref docs.

- The units of information that make up Conventional Commits MUST NOT be treated as case sensitive by implementors, with the exception of BREAKING CHANGE which MUST be uppercase.

- BREAKING-CHANGE MUST be synonymous with BREAKING CHANGE, when used as a token in a footer.


https://www.conventionalcommits.org/en/v1.0.0/